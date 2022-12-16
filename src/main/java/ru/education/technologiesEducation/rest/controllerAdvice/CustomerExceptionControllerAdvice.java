package ru.education.technologiesEducation.rest.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.education.technologiesEducation.exceptions.StatisticRecordNotFoundException;
import ru.education.technologiesEducation.exceptions.UserNotFoundException;

@ControllerAdvice
public class CustomerExceptionControllerAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus
    public String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StatisticRecordNotFoundException.class)
    @ResponseStatus
    public String recordNotFoundHandler(StatisticRecordNotFoundException ex) { return ex.getMessage(); }
}
