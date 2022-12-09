package ru.education.technologiesEducation.services;

import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.User;

public interface UserService {

    void save(AuthenticationRequestUserDto user);

    User findByUsername(String username);
}
