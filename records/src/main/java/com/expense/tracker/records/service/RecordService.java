package com.expense.tracker.records.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.records.apis.RecordFacade;
import com.expense.tracker.records.dto.RecordList;
import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.repository.RecordSaveRepository;
import com.expense.tracker.records.vo.UpdateBudgetDTO;

@Service
public class RecordService {

	@Autowired
	RecordSaveRepository recordRepo;

	@Autowired
	RecordFacade rf;
	Logger log = LoggerFactory.getLogger(this.getClass());

	public String addRecord(RecordEntity records) {

		RecordEntity res = new RecordEntity();
		res = recordRepo.save(records);

		if (res != null) {

			UpdateBudgetDTO updateBud = new UpdateBudgetDTO();
			updateBud.setCategory(records.getCategory());
			updateBud.setSpent(records.getAmount());
			rf.updateSpent(updateBud);
		}

		return "updated";

	}

	public List<RecordList> getRecordHistory(String month, String year) {
		List<RecordList> response = new ArrayList<>();

		String monthYear = month + "-" + year;
		log.info("The month and year is-->" + monthYear);

		List<RecordEntity> recordList = new ArrayList();

		recordList = recordRepo.findByMonthYear(monthYear);

		log.info("The output record --> " + recordList);
		
		recordList.forEach(obj->{
			LocalDate d1=obj.getDate();
		});

		return null;
	}

}
