package ru.education.technologiesEducation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.education.technologiesEducation.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
