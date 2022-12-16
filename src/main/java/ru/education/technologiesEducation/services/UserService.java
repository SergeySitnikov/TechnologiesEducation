package ru.education.technologiesEducation.services;

import org.springframework.security.core.Authentication;
import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.UserStatisticRecord;

import java.util.List;

public interface UserService {

    void save(AuthenticationRequestUserDto user);

    Customer getByUsername(String username);

    Customer getUserById(Long id);

    Customer getUserByAuthentication(Authentication authentication);

    List<Customer> getAllUsers();

    List<UserStatisticRecord> getAllStatisticRecords(Long customerId);

    UserStatisticRecord getStatisticRecord(String name, Long customerId);

    void delete(Long id);
}
