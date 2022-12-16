package ru.education.technologiesEducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education.technologiesEducation.dao.UserStatisticRecordRepository;
import ru.education.technologiesEducation.dto.UserStatisticRecordDto;
import ru.education.technologiesEducation.exceptions.StatisticRecordNotFoundException;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.services.UserStatisticRecordService;

import java.util.Date;

@Service
public class UserStatisticRecordServiceImpl implements UserStatisticRecordService {

    private UserStatisticRecordRepository userStatisticRecordRepository;
    private UserService userService;

    @Autowired
    public UserStatisticRecordServiceImpl(UserStatisticRecordRepository userStatisticRecordRepository, UserService userService) {
        this.userStatisticRecordRepository = userStatisticRecordRepository;
        this.userService = userService;
    }

    @Override
    public void save(UserStatisticRecordDto userStatisticRecordDto, Customer customer) {
        UserStatisticRecord record = new UserStatisticRecord();
        record.setRecordName(userStatisticRecordDto.getName());
        record.setDescription(userStatisticRecordDto.getDescription());
        record.setCustomer(customer);
        record.setNumber(userStatisticRecordDto.getNumber());
        record.setCreationDate(new Date());
        userStatisticRecordRepository.save(record);
    }

    @Override
    public UserStatisticRecord update(String originalName, UserStatisticRecordDto userStatisticRecordDto, Long customerId) {
        UserStatisticRecord record = userStatisticRecordRepository.findByRecordNameAndCustomerId(originalName, customerId);
        if (record == null) {
            throw new StatisticRecordNotFoundException("Statistic record with name: " + originalName + " not found");
        }
        String name = userStatisticRecordDto.getName();
        if (name != null && !name.equals("") && !name.equals(originalName)) {
            record.setRecordName(name);
        }
        String description = userStatisticRecordDto.getDescription();
        if (description != null && !description.equals(originalName)) {
            record.setDescription(description);
        }
        Long number = userStatisticRecordDto.getNumber();
        if (number != null && !number.equals(record.getNumber())) {
            record.setNumber(number);
        }
        userStatisticRecordRepository.save(record);
        return record;

    }

    @Override
    public void delete(String name, Long customerId) {
        userStatisticRecordRepository.deleteByRecordNameAndCustomerId(name, customerId);
    }
}
