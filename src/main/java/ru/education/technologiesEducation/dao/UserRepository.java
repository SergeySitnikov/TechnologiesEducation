package ru.education.technologiesEducation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.education.technologiesEducation.model.Customer;

public interface UserRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
