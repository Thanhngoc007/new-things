package fa.training.studentmanagement.controller;

import fa.training.studentmanagement.component.PasswordEncrypt;
import fa.training.studentmanagement.dto.FaClassDisplayDto;
import fa.training.studentmanagement.dto.FaClassStudentEnroll;
import fa.training.studentmanagement.dto.StudentDisplayDto;
import fa.training.studentmanagement.dto.StudentParamDto;
import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.entity.FaClassStudent;
import fa.training.studentmanagement.entity.Student;
import fa.training.studentmanagement.enums.CRUD;
import fa.training.studentmanagement.service.FaClassService;
import fa.training.studentmanagement.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final FaClassService faClassService;
    private PasswordEncrypt passwordEncrypt;

    public StudentController(StudentService studentService,
                             FaClassService faClassService) {
        this.studentService = studentService;
        this.faClassService = faClassService;
    }

    @Autowired
    public void setPasswordEncrypt(PasswordEncrypt passwordEncrypt) {
        this.passwordEncrypt = passwordEncrypt;
    }

    @Value("${app.student.default-password}")
    String defaultPassword;

    @GetMapping()
    public String showStudentList(Model model) {
//        List<Student> studentList = studentService.getEnrollStudentList();
        List<Student> studentList = studentService.getAll();
        List<StudentDisplayDto> studentDisplayDtoList =
                studentList.stream()
                            .map(Student::convertToDisplayDto)
                            .collect(Collectors.toList());
        // Convert studentList -> studentDisplayDtoList
        model.addAttribute("studentList", studentDisplayDtoList);

        return "student/student-list";
    }

    @GetMapping("/add")
    public String showAddStudent(Model model) {
        StudentParamDto studentDto = new StudentParamDto();
        model.addAttribute("studentDto", studentDto);
        initCourseList(model);

        return "student/student-form";
    }

    @GetMapping("/update/{studentId}")
    public String showUpdateStudent(@PathVariable(name = "studentId") Long id,
                                    Model model) {
        Optional<Student> studentOptional = studentService.findOne(id);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("Can not find student with id: " + id);
        }
        Student student = studentOptional.get();
        StudentParamDto studentDto = new StudentParamDto();
        BeanUtils.copyProperties(student, studentDto);

        if (student.getFaClassStudents() != null) {
            // List<Long> => Long[]
            List<Long> classIdList = student.getFaClassStudents().stream()
                                        .map(cs -> cs.getFaClass().getId())
                                        .collect(Collectors.toList());
            studentDto.setClassIdList(classIdList.toArray(new Long[]{}));
        }

        model.addAttribute("studentDto", studentDto);
        initCourseList(model);

        return "student/student-form";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("studentDto") @Valid StudentParamDto studentParamDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            boolean existEmail = studentService.existEmail(studentParamDto.getEmail());
            if (existEmail) {
                bindingResult.rejectValue
                        ("email", "student.email.existing", "Email is existing, please try again");
            }
            // Check other error
        }

        // Add global error
//        bindingResult.reject("student.invalid");
//        bindingResult.reject("student.invalid", "ABCD");

        if (bindingResult.hasErrors()) {
            return "student/student-form";
        }

        // Convert studenParamtDt0 -> student entity
        Student student = new Student();
        BeanUtils.copyProperties(studentParamDto, student);
        // Setting password default
        student.setPassword(passwordEncrypt.encrypt(defaultPassword));
        if (studentParamDto.getClassIdList() != null) {
            // Class Id (Long) => FaClassStudent
            Set<FaClassStudent> faClassStudents = Arrays.stream(studentParamDto.getClassIdList())
                    .map(classId -> new FaClassStudent(student,
                                                       FaClass.builder().id(classId).build(),
                                                        LocalDateTime.now()))
                    .collect(Collectors.toSet());
            String string = Arrays.stream(studentParamDto.getClassIdList()).map(Object::toString).collect(Collectors.joining(","));
            student.setClassCode(string);

//            Set<FaClassStudentEnroll> faClassStudentEnrolls = Arrays.stream(studentParamDto.getClassIdList())
//                    .map(classId -> new FaClassStudentEnroll(student,
//                            FaClass.builder().id(classId).build(),
//                            LocalDateTime.now(),FaClass.builder().classCode(student.getClassCode())))
//                    .collect(Collectors.toSet());

//            Set<FaClassStudentEnroll> faClassStudentEnrolls = Arrays.stream(studentParamDto.getClassIdList())
//                    .map(classId -> new FaClassStudentEnroll(student,
//                            FaClass.builder().id(classId).build(),
//                            LocalDateTime.now(), FaClass.builder().id(classId).build().getClassCode().toString()))
//                    .collect(Collectors.toSet());

//            student.setClassCode(studentParamDtoDto.getClassIdList());

//            faClass.setTimeSlot(paramDto.getTimeSlot() != null
//                    ? String.join(",", paramDto.getTimeSlot()) : null);
//            student.setClassCode();
//            Set<Student> students = Arrays.stream(studentParamDtoDto.getClassIdList().)
//                    .map(classId -> new Student(student,
//                            FaClass.builder().id(classId).build(),
//                            LocalDateTime.now()))
//                    .collect(Collectors.toSet());

            student.setFaClassStudents(faClassStudents);
        }

        studentService.create(student);

          redirectAttributes.addFlashAttribute("message", "student.add.success");
          redirectAttributes.addFlashAttribute("messageNative", "Add student success (Native)!");

        return "redirect:/students";
    }

    @PostMapping("/update/{id}") // end point
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("studentDto") @Valid StudentParamDto studentDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student/student-form";
        }

        Optional<Student> studentOptional = studentService.findOne(id);
        if (!studentOptional.isPresent()) {
            throw new IllegalArgumentException("Can not find student with id: " + id);
        }
        Student student = studentOptional.get();
        BeanUtils.copyProperties(studentDto, student, "email", "password");
        if (studentDto.getClassIdList() != null) {
            // Class Id (Long) => FaClassStudent
            Set<FaClassStudent> faClassStudents = Arrays.stream(studentDto.getClassIdList())
                    .map(classId -> new FaClassStudent(student,
                            FaClass.builder().id(classId).build(),
                            LocalDateTime.now()))
                    .collect(Collectors.toSet());
            String string = Arrays.stream(studentDto.getClassIdList()).map(Object::toString).collect(Collectors.joining(","));
            student.setClassCode(string);
            student.setFaClassStudents(faClassStudents);
        }
        studentService.update(student);

        return "redirect:/students?mode=" + CRUD.UPDATE.name().toLowerCase();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        return "redirect:/students";
    }

    private void initCourseList(Model model) {
        List<FaClass> faClassList = faClassService.getAll();
        List<FaClassDisplayDto> classDtoList = faClassList.stream().map(faClass -> {
            FaClassDisplayDto displayDto = new FaClassDisplayDto();
            BeanUtils.copyProperties(faClass, displayDto);
            return displayDto;
        }).collect(Collectors.toList());
        model.addAttribute("classDtoList", classDtoList);
    }

//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody @Valid StudentParamDto studentParamDto){
//
//        Student student = new Student();
//        BeanUtils.copyProperties(studentParamDto, student);
//        // Setting password default
//        student.setPassword(passwordEncrypt.encrypt(defaultPassword));
//        if (studentParamDto.getClassIdList() != null) {
//            // Class Id (Long) => FaClassStudent
//            Set<FaClassStudent> faClassStudents = Arrays.stream(studentParamDto.getClassIdList())
//                    .map(classId -> new FaClassStudent(student,
//                            FaClass.builder().id(classId).build(),
//                            LocalDateTime.now()))
//                    .collect(Collectors.toSet());
//
//            student.setFaClassStudents(faClassStudents);
//        }
//
//        studentService.create(student);
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
