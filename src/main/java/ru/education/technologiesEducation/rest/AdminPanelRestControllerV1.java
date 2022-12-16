package ru.education.technologiesEducation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.technologiesEducation.dto.AdminPanelUserDto;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.staticValues.URLNames;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(URLNames.ADMIN_PANEL_MAPPING_URL)
public class AdminPanelRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminPanelRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AdminPanelUserDto>> getUsers() {
        List<Customer> allUsers = userService.getAllUsers();
        List<AdminPanelUserDto> result = new ArrayList<>();
        for (Customer user : allUsers) {
            AdminPanelUserDto adminPanelUserDto = new AdminPanelUserDto();
            adminPanelUserDto.setUsername(user.getUsername());
            adminPanelUserDto.setRoles(user.getRoles());
            adminPanelUserDto.setStatus(user.getStatus());
            adminPanelUserDto.setStatisticRecordCounter((long) userService.getAllStatisticRecords(user.getId()).size());
            result.add(adminPanelUserDto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
