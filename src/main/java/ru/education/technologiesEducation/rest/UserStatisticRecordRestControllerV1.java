package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.dto.UserStatisticRecordDto;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.services.UserStatisticRecordService;
import ru.education.technologiesEducation.staticValues.URLNames;
import ru.education.technologiesEducation.validators.UserStatisticRecordValidator;
import ru.education.technologiesEducation.validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLNames.USER_STATISTIC_RECORD_URL)
public class UserStatisticRecordRestControllerV1 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatisticRecordService recordService;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserStatisticRecordValidator());
    }

    @GetMapping
    public ResponseEntity<List<UserStatisticRecordDto>> getRecords(Authentication authentication) {
        List<UserStatisticRecord> allStatisticRecords = userService.getAllStatisticRecords(userService.getUserByAuthentication(authentication).getId());
        return new ResponseEntity<>(allStatisticRecords.stream()
                .map(this::recordToRecordDto)
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @GetMapping("/{name}")
    public ResponseEntity<UserStatisticRecordDto> getRecord(@PathVariable String name, Authentication authentication) {
        return new ResponseEntity<>(recordToRecordDto(userService.getStatisticRecord(name, userService.getUserByAuthentication(authentication).getId())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createRecord(@RequestBody @Validated UserStatisticRecordDto userStatisticRecordDto, Authentication authentication) {
        recordService.save(userStatisticRecordDto, userService.getUserByAuthentication(authentication));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<UserStatisticRecordDto> updateRecord(@PathVariable String name, @RequestBody UserStatisticRecordDto userStatisticRecordDto, Authentication authentication) {
        return new ResponseEntity<>(recordToRecordDto(recordService.update(name, userStatisticRecordDto, userService.getUserByAuthentication(authentication).getId())), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String name, Authentication authentication) {
        recordService.delete(name, userService.getUserByAuthentication(authentication).getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserStatisticRecordDto recordToRecordDto(UserStatisticRecord record) {
        return new UserStatisticRecordDto(
                record.getRecordName(),
                record.getNumber(),
                record.getDescription(),
                record.getCreationDate());
    }
}
