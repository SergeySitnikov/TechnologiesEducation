package ru.education.technologiesEducation.exceptions;

public class StatisticRecordNotFoundException extends RuntimeException{
    public StatisticRecordNotFoundException() {
        super();
    }

    public StatisticRecordNotFoundException(String message) {
        super(message);
    }

    public StatisticRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
