package ru.education.technologiesEducation.services;

import ru.education.technologiesEducation.dto.UserStatisticRecordDto;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.UserStatisticRecord;

public interface UserStatisticRecordService {
    void save(UserStatisticRecordDto userStatisticRecordDto, Customer customer);

    UserStatisticRecord update(String originalName, UserStatisticRecordDto userStatisticRecordDto, Long customerId);

    void delete(String name, Long customerId);
}
