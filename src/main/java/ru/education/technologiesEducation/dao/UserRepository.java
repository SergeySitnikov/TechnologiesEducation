package ru.education.technologiesEducation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.education.technologiesEducation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
