package ru.education.technologiesEducation.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(Customer customer) {
        return new JwtUser(
                customer.getId(),
                customer.getUsername(),
                customer.getPassword(),
                Status.ACTIVE.equals(customer.getStatus()),
                mapToGrantedAuthorities(new ArrayList<>(customer.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
