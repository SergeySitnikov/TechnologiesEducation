package ru.education.technologiesEducation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.education.technologiesEducation.dto.UserStatisticRecordDto;

@Component
public class UserStatisticRecordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserStatisticRecordDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserStatisticRecordDto recordDto = (UserStatisticRecordDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
    }
}
