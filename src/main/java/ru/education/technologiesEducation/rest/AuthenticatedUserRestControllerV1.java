package ru.education.technologiesEducation.rest;

import org.springframework.web.bind.annotation.*;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.exceptions.UserNotFoundException;
import ru.education.technologiesEducation.staticValues.EntityFieldName;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class AuthenticatedUserRestControllerV1 {

    @GetMapping("{username}")
    public Customer getUserStatistic(@PathVariable String username) {
        return getUser(username);
    }

    private List<String> getUserNames() {

        return Collections.emptyList();
    }

    private Customer getUser(String username) {
        //user.increaseRequestCount();
        return null;
    }

}
