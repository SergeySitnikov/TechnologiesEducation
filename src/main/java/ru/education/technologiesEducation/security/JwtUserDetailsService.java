package ru.education.technologiesEducation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.technologiesEducation.dao.UserRepository;
import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.security.jwt.JwtUser;
import ru.education.technologiesEducation.security.jwt.JwtUserFactory;

import java.util.HashSet;
import java.util.Set;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Customer customer = userRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return JwtUserFactory.create(customer);
    }
}
