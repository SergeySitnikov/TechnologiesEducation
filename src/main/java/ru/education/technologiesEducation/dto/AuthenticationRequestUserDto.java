package ru.education.technologiesEducation.dto;

public class AuthenticationRequestUserDto {
    private String username;
    private String password;

    public AuthenticationRequestUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
