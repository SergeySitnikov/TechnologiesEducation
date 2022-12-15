package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.dto.UserStatisticRecordDto;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.staticValues.URLNames;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLNames.USER_STATISTIC_RECORD_URL)
public class UserStatisticRecordRestControllerV1 {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserStatisticRecordDto>> getRecords(Authentication authentication) {
        List<UserStatisticRecord> allStatisticRecords = userService.getAllStatisticRecords(authentication);
        return new ResponseEntity<>(allStatisticRecords.stream()
                .map(record -> new UserStatisticRecordDto(
                        record.getRecordName(),
                        record.getNumber()
                )).collect(Collectors.toList()), HttpStatus.OK);

    }

}
