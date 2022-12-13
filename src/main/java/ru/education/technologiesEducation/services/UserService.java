package ru.education.technologiesEducation.services;

import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.Customer;

public interface UserService {

    void save(AuthenticationRequestUserDto user);

    Customer findByUsername(String username);
}
