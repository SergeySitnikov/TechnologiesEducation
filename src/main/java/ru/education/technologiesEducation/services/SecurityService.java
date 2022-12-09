package ru.education.technologiesEducation.services;

public interface SecurityService {
    String findLoggedUsername();
    void autoLogin(String username, String password);
}
