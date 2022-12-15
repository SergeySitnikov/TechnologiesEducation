package ru.education.technologiesEducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.education.technologiesEducation.dao.RoleRepository;
import ru.education.technologiesEducation.dao.UserRepository;
import ru.education.technologiesEducation.dao.UserStatisticRecordRepository;
import ru.education.technologiesEducation.dto.AuthenticationRequestUserDto;
import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Status;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserStatisticRecordRepository statisticRecordRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserStatisticRecordRepository statisticRecordRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.statisticRecordRepository = statisticRecordRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(AuthenticationRequestUserDto userDto) {
        Customer customer = new Customer();
        customer.setUsername(userDto.getUsername());
        customer.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getReferenceById(2L));
        customer.setRoles(roles);
        customer.setStatus(Status.ACTIVE);
        userRepository.save(customer);
    }

    @Override
    public Customer getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Customer getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Customer getUserByAuthentication(Authentication authentication) {
        return this.getByUsername(((UserDetails)(authentication.getPrincipal())).getUsername());
    }

    @Override
    public List<UserStatisticRecord> getAllStatisticRecords(Authentication authentication) {
        return statisticRecordRepository.findAllByCustomerId(this.getUserByAuthentication(authentication).getId());
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(this.getUserById(id));
    }
}
