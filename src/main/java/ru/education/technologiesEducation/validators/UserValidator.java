package ru.education.technologiesEducation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.services.UserService;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthenticationRequestUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthenticationRequestUserDto customer = (AuthenticationRequestUserDto)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (customer.getUsername().length() < 8 || customer.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(customer.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (customer.getPassword().length() < 8 || customer.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!customer.getConfirmPassword().equals(customer.getPassword())) {
            errors.rejectValue("password", "Different.userForm.password");
        }
    }
}
