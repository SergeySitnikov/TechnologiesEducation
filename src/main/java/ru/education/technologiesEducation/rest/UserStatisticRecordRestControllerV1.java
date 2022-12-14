package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.staticValues.URLNames;

import java.util.List;

@RestController
@RequestMapping(URLNames.USER_STATISTIC_RECORD_URL)
public class UserStatisticRecordRestControllerV1 {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserStatisticRecord>> getRecords(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllStatisticRecords(authentication));
    }

}
