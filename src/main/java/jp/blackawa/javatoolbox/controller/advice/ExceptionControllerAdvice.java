package jp.blackawa.javatoolbox.controller.advice;

import jp.blackawa.javatoolbox.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = {NotFoundException.class})
    public ModelAndView notFound() {
        return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
    }
}
