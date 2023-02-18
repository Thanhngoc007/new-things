package fa.training.studentmanagement.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public String notFound() {
        return "/error/404";
    }

    @ExceptionHandler({Exception.class})
    public String defaultError(Exception e) {
        e.printStackTrace();
        return "/error/500";
    }

}
