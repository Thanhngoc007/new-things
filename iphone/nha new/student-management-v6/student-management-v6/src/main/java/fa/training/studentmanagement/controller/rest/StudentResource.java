package fa.training.studentmanagement.controller.rest;

import fa.training.studentmanagement.dto.StudentDisplayDto;
import fa.training.studentmanagement.entity.Student;
import fa.training.studentmanagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentResource {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDisplayDto>> getStudentList() {
        List<Student> studentList = studentService.getAll();
        List<StudentDisplayDto> studentDisplayDtoList =
                studentList.stream()
                        .map(Student::convertToDisplayDto)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(studentDisplayDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDisplayDto> getById(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentService.findOne(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return ResponseEntity.ok(student.convertToDisplayDto());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentService.findOne(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentService.delete(student);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}