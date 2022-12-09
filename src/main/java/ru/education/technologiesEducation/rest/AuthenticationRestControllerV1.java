package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.exceptions.UserNotFoundException;
import ru.education.technologiesEducation.model.User;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.validators.UserValidator;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator(userService));
    }

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserService userService, UserValidator validator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.validator = validator;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestUserDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
            User user = userService.findByUsername(requestDto.getUsername());
            if (user == null) {
                throw new UserNotFoundException("User with username: " + requestDto.getUsername() + " not found");
            }
            return ResponseEntity.ok("Ok");
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@Validated @RequestBody AuthenticationRequestUserDto requestDto) {
        userService.save(requestDto);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        return ResponseEntity.ok("User successfully registered");
    }
}
