package ru.education.technologiesEducation.rest.controllerAdvice;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.education.technologiesEducation.exceptions.StatisticRecordNotFoundException;
import ru.education.technologiesEducation.exceptions.UserNotFoundException;
import ru.education.technologiesEducation.exceptions.UserStatisticErrorException;

@ControllerAdvice
public class CustomerExceptionControllerAdvice {
    @ResponseBody
    @ExceptionHandler({BadCredentialsException.class,
            UserStatisticErrorException.class,
            StatisticRecordNotFoundException.class,
            UserNotFoundException.class})
    @ResponseStatus
    public String exceptionHandlerWithMessageResult(BadCredentialsException ex) {
        return ex.getMessage();
    }
}
