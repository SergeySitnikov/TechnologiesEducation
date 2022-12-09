package ru.education.technologiesEducation.rest;

import org.springframework.web.bind.annotation.*;
import ru.education.technologiesEducation.model.User;
import ru.education.technologiesEducation.exceptions.UserNotFoundException;
import ru.education.technologiesEducation.staticValues.EntityFieldName;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class MainPageController {

    Map<String, User> users = new HashMap<>();

    private final AtomicLong userIds = new AtomicLong();


    @GetMapping
    public List<String> mainPageController() {
        return getUserNames();
    }

    @GetMapping("{username}")
    public User getUserStatistic(@PathVariable String username) {
        return getUser(username);
    }

    @PostMapping
    public List<String> addUser(@RequestBody Map<String, String> userData) {
        return getUserNames();
    }

    @PutMapping({"{username}"})
    public User updateUser(@PathVariable String username, @RequestBody Map<String, String> userData) {
        User user = getUser(username);
        user.setUsername(userData.getOrDefault(EntityFieldName.USERNAME, username));
        users.remove(username);
        users.put(user.getUsername(), user);
        return user;
    }

    @DeleteMapping({"{username}"})
    public void deleteUser(@PathVariable String username) {
        getUser(username);
        users.remove(username);
    }

    private List<String> getUserNames() {
        if (users.size() > 0) {
            return users.values().stream().map(User::getUsername).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private User getUser(String username) {
        if (!users.containsKey(username)) {
            throw new UserNotFoundException();
        }
        User user = users.get(username);
        user.increaseRequestCount();
        return user;
    }

}
