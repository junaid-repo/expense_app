package com.expense.tracker.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.records.entity.RecordEntity;

public interface RecordSaveRepository extends JpaRepository<RecordEntity, Integer> {

	@Query(value = "select v.* from records v where to_char(v.date, 'MM-YYYY') = ?1", nativeQuery = true)
	List<RecordEntity> findByMonthYear(String monthYear);

}
