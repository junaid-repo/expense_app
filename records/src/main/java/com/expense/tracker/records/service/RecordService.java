package com.expense.tracker.records.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.records.apis.RecordFacade;
import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.repository.RecordSaveRepository;
import com.expense.tracker.records.vo.UpdateBudgetDTO;

@Service
public class RecordService {
	
	@Autowired
	RecordSaveRepository recordRepo;
	
	@Autowired
	RecordFacade rf;

	public String addRecord(RecordEntity records) {

		RecordEntity res = new RecordEntity();
		res=recordRepo.save(records);
		
		if(res!=null) {
			
			UpdateBudgetDTO updateBud = new UpdateBudgetDTO();
			updateBud.setCategory(records.getCategory());
			updateBud.setSpent(records.getAmount());
			rf.updateSpent(updateBud);
		}
		
		return "updated";
		
	}
	

}
