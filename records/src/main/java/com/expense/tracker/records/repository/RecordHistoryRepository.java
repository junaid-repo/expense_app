package com.expense.tracker.records.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.records.entity.RecordHistoryEntity;

public interface RecordHistoryRepository extends JpaRepository<RecordHistoryEntity, Integer> {

	@Query(value = "select d.* from records_history d where d.date=?1", nativeQuery = true)
	RecordHistoryEntity findIdsByDate(LocalDate d1);

	@Query(value = "select v.* from records_history v where to_char(v.date, 'MM-YYYY') = ?1", nativeQuery = true)
	List<RecordHistoryEntity> findByMonthYear(String monthYear);

}
