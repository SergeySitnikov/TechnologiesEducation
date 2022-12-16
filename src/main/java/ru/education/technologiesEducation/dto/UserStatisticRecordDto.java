package ru.education.technologiesEducation.dto;

import java.util.Date;

public class UserStatisticRecordDto {
    private String name;
    private Long number;

    private String description;

    private Date creationDate;

    public UserStatisticRecordDto(String name, Long number, String description, Date creationDate) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.creationDate = creationDate;
    }

    public UserStatisticRecordDto(String name, Long number, String description) {
        this.name = name;
        this.number = number;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
