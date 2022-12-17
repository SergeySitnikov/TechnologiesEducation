package ru.education.technologiesEducation.dto;

import ru.education.technologiesEducation.model.Role;
import ru.education.technologiesEducation.model.Status;

import java.util.List;

public class AdminPanelUserDto {
    private String username;
    private Status status;
    private Long statisticRecordCounter;

    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getStatisticRecordCounter() {
        return statisticRecordCounter;
    }

    public void setStatisticRecordCounter(Long statisticRecordCounter) {
        this.statisticRecordCounter = statisticRecordCounter;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
