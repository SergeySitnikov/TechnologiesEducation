package ru.education.technologiesEducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.education.technologiesEducation.dao.RoleRepository;
import ru.education.technologiesEducation.dao.UserRepository;
import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Status;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(AuthenticationRequestUserDto userDto) {
        Customer customer = new Customer();
        customer.setUsername(userDto.getUsername());
        customer.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getReferenceById(2L));
        customer.setRoles(roles);
        customer.setStatus(Status.ACTIVE);
        userRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
