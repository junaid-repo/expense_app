package com.expense.tracker.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.records.entity.RecordEntity;

public interface RecordSaveRepository extends JpaRepository<RecordEntity, Integer> {

}
