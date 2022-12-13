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

    @PostMapping
    public List<String> addUser(@RequestBody Map<String, String> userData) {
        return getUserNames();
    }

    @PutMapping({"{username}"})
    public Customer updateUser(@PathVariable String username, @RequestBody Map<String, String> userData) {
        Customer customer = getUser(username);
        customer.setUsername(userData.getOrDefault(EntityFieldName.USERNAME, username));
        return customer;
    }

    @DeleteMapping({"{username}"})
    public void deleteUser(@PathVariable String username) {
        getUser(username);
    }

    private List<String> getUserNames() {

        return Collections.emptyList();
    }

    private Customer getUser(String username) {
        //user.increaseRequestCount();
        return null;
    }

}
