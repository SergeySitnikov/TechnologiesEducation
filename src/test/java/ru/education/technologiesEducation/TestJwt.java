package ru.education.technologiesEducation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Status;
import ru.education.technologiesEducation.security.jwt.JwtUser;
import ru.education.technologiesEducation.security.jwt.JwtUserFactory;

import java.util.Collections;
import java.util.List;

public class TestJwt {

    @Test
    public void testCreateJwtUser() {
        JwtUser jwtUser = new JwtUser(1L, "Test", "123456789", true, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("Test");
        customer.setPassword("123456789");
        customer.setStatus(Status.ACTIVE);
        Role role = new Role();
        role.setId(1L);
        role.setName("ADMIN");
        customer.setRoles(List.of(role));
        assertEquals(jwtUser, JwtUserFactory.create(customer));
    }
}
