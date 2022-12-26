package ru.education.technologiesEducation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.education.technologiesEducation.dao.UserStatisticRecordRepository;
import ru.education.technologiesEducation.dto.UserStatisticRecordDto;
import ru.education.technologiesEducation.exceptions.StatisticRecordNotFoundException;
import ru.education.technologiesEducation.exceptions.UserStatisticErrorException;
import ru.education.technologiesEducation.model.Customer;
import ru.education.technologiesEducation.model.UserStatisticRecord;
import ru.education.technologiesEducation.services.UserService;
import ru.education.technologiesEducation.services.UserStatisticRecordService;
import ru.education.technologiesEducation.services.impl.UserServiceImpl;
import ru.education.technologiesEducation.services.impl.UserStatisticRecordServiceImpl;

import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestUserStatisticRecord {

    private static UserStatisticRecord record1;
    private static UserStatisticRecord record2;
    private static UserStatisticRecord record3;

    @Mock
    private UserStatisticRecordRepository recordRepository;

    private static Customer customer;
    private UserService userService;

    private UserStatisticRecordService recordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserServiceImpl(null, null, recordRepository, null);
        this.recordService = new UserStatisticRecordServiceImpl(recordRepository);
    }

    @BeforeAll
    public static void init() {
        customer = new Customer();
        customer.setId(1L);
        customer.setUsername("Customer");
        record1 = new UserStatisticRecord(1L, "record1", customer, 1L, "desc1", new Date());
        record2 = new UserStatisticRecord(2L, "record2", customer, 2L, "desc2", new Date());
        record3 = new UserStatisticRecord(3L, "record3", customer, 3L, "desc3", new Date());
    }

    @Test
    public void findAll() {
        given(recordRepository.findAllByCustomerId(1L)).willReturn(List.of(record1, record2, record3));
        List<UserStatisticRecord> allStatisticRecords = userService.getAllStatisticRecords(customer.getId());
        Assertions.assertEquals(3, allStatisticRecords.size());
    }

    @Test
    public void saveRecord_Successful() {
        given(recordRepository.findByRecordNameAndCustomerId("record1", customer.getId())).willReturn(null);
        recordService.save(new UserStatisticRecordDto(record1), customer);
    }
    @Test
    public void saveRecord_Exception_Throw() {
        given(recordRepository.findByRecordNameAndCustomerId("record1", customer.getId())).willReturn(record1);
        Assertions.assertThrows(UserStatisticErrorException.class, () -> recordService.save(new UserStatisticRecordDto(record1), customer));
    }

    @Test
    public void updateRecord_Successful() {
        given(recordRepository.findByRecordNameAndCustomerId("record1", customer.getId())).willReturn(record1);
        UserStatisticRecord expectedRecord = record1;
        expectedRecord.setRecordName("tmp");
        Assertions.assertEquals(expectedRecord, recordService.update("record1", new UserStatisticRecordDto(expectedRecord), customer.getId()));
    }

    @Test
    public void updateRecord_Record_Not_Found() {
        given(recordRepository.findByRecordNameAndCustomerId("record1", customer.getId())).willReturn(null);
        Assertions.assertThrows(StatisticRecordNotFoundException.class, () -> recordService.update("record1", new UserStatisticRecordDto(record1), customer.getId()));
    }
}
