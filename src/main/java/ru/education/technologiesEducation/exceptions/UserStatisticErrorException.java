package ru.education.technologiesEducation.exceptions;

public class UserStatisticErrorException extends RuntimeException{
    public UserStatisticErrorException() {
        super();
    }

    public UserStatisticErrorException(String message) {
        super(message);
    }

    public UserStatisticErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
