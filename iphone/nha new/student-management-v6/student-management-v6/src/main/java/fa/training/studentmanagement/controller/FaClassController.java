package fa.training.studentmanagement.controller;

import fa.training.studentmanagement.dto.FaClassDisplayDto;
import fa.training.studentmanagement.dto.FaClassParamDto;
import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.dto.StudentParamDto;
import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.service.FaClassService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/classes")
public class FaClassController extends BaseController {

    final FaClassService faClassService;

    @GetMapping
    public String showList(Model model) {
//        List<FaClass> faClassList = faClassService.getAll();
//        List<FaClassDisplayDto> classDtoList = faClassList.stream().map(faClass -> {
//            FaClassDisplayDto displayDto = new FaClassDisplayDto();
//            BeanUtils.copyProperties(faClass, displayDto);
//            return displayDto;
//        }).collect(Collectors.toList());

        List<FaClassSummaryDto> classDtoList = faClassService.getFaClassSummary();

        model.addAttribute("classList", classDtoList);

        return "faClass/class-list";
    }

//    @GetMapping
//    public ModelAndView showList(Model model) {
//        List<FaClass> faClassList = faClassService.getAll();
//        List<FaClassDisplayDto> classDtoList = faClassList.stream().map(faClass -> {
//            FaClassDisplayDto displayDto = new FaClassDisplayDto();
//            BeanUtils.copyProperties(faClass, displayDto);
//            return displayDto;
//        }).collect(Collectors.toList());
//        model.addAttribute("classList", classDtoList);
//
//        ModelAndView modelAndView = new ModelAndView("faClass/class-list");
//        modelAndView.addObject(model);
//
//        return modelAndView;
//    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        FaClassParamDto faClassParamDto = new FaClassParamDto();
        model.addAttribute("faClassDto", faClassParamDto);

        return "faClass/class-form";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable Long id,
                             Model model) {
        Optional<FaClass> faClassOptional = faClassService.findOne(id);
        if (!faClassOptional.isPresent()) {
            throw new IllegalArgumentException("Can not found the class with id: " + id);
        }
        FaClass faClass = faClassOptional.get();
        FaClassParamDto paramDto = new FaClassParamDto();
        BeanUtils.copyProperties(faClass, paramDto);
        // String -> String[]
        paramDto.setTimeSlot(faClass.getTimeSlot() != null ?
                faClass.getTimeSlot().split(",") : null);
        model.addAttribute("faClassDto", paramDto);

        return "faClass/class-form";
    }

    @PostMapping("/add")
    public String add(@Valid FaClassParamDto paramDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "faClass/class-form";
        }
        FaClass faClass = new FaClass();
        BeanUtils.copyProperties(paramDto, faClass);
        // String[] -> String
        faClass.setTimeSlot(paramDto.getTimeSlot() != null
                ? String.join(",", paramDto.getTimeSlot()) : null);

        faClassService.create(faClass);
        redirectAttributes.addFlashAttribute("message", "faClass.add.success");
        return "redirect:/classes";
    }
}
