package ru.education.technologiesEducation.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.security.jwt.JwtTokenProvider;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.staticValues.URLNames;
import ru.education.technologiesEducation.validators.UserValidator;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = URLNames.AUTHENTICATION_REQUEST_MAPPING_URL)
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator(userService));
    }

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestUserDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
            Customer customer = userService.getByUsername(requestDto.getUsername());
            if (customer == null) {
                throw new UserNotFoundException("User with username: " + requestDto.getUsername() + " not found");
            }
            Map<String, String> response = new HashMap<>();
            response.put("username", customer.getUsername());
            response.put("token", jwtTokenProvider.createToken(customer.getUsername(), customer.getRoles()));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@Validated @RequestBody AuthenticationRequestUserDto requestDto) {
        userService.save(requestDto);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }
}
