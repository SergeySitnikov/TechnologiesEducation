package ru.education.technologiesEducation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.education.technologiesEducation.model.UserStatisticRecord;

import java.util.List;

public interface UserStatisticRecordRepository extends JpaRepository<UserStatisticRecord, Long> {
    UserStatisticRecord findByRecordName(String recordName);
    List<UserStatisticRecord> findAllByCustomerId(Long customerId);
}
