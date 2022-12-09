package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.services.SecurityService;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.validators.UserValidator;

@RestController
public class UserRestController {

    private UserService userService;

    private UserValidator userValidator;

    private SecurityService securityService;

    @Autowired
    public UserRestController(UserService userService, UserValidator userValidator, SecurityService securityService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }


}
