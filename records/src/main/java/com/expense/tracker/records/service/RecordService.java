package com.expense.tracker.records.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expense.tracker.records.advice.InvalidRequestData;
import com.expense.tracker.records.apis.RecordFacade;
import com.expense.tracker.records.dto.RecordHistoryDto;
import com.expense.tracker.records.dto.RecordList;
import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.entity.RecordHistoryEntity;
import com.expense.tracker.records.repository.RecordHistoryRepository;
import com.expense.tracker.records.repository.RecordSaveRepository;
import com.expense.tracker.records.vo.CategoryEntity;
import com.expense.tracker.records.vo.UpdateBudgetDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class RecordService {

	@Autowired
	RecordSaveRepository recordRepo;
	@Autowired
	RecordHistoryRepository recordHisRepo;

	@Autowired
	RecordFacade rf;
	Logger log = LoggerFactory.getLogger(this.getClass());

	public String addRecord(RecordEntity records) throws InvalidRequestData {

		boolean check = validateRecordsRequest(records);

		if (check) {

			RecordEntity res = new RecordEntity();
			// records.setDate(records.getDate().plusDays(1));
			try {
				res = recordRepo.save(records);
			} catch (Exception e) {

				e.printStackTrace();
			}

			if (res != null) {

				String recordsIds = "";
				RecordHistoryEntity rhe = new RecordHistoryEntity();
				LocalDate d1 = records.getDate();
				try {
					rhe = recordHisRepo.findIdsByDate(d1);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (rhe != null) {
					recordsIds = rhe.getRecordIds();
					recordsIds = recordsIds + "##" + records.getId();
					RecordHistoryEntity rce = new RecordHistoryEntity();
					RecordHistoryEntity rce2 = new RecordHistoryEntity();

					// rce.setDate(d1);
					rhe.setRecordIds(recordsIds);
					try {
						rce2 = recordHisRepo.save(rhe);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					RecordHistoryEntity rhe2 = new RecordHistoryEntity();
					rhe2.setRecordIds(String.valueOf(records.getId()));
					rhe2.setDate(records.getDate());
					RecordHistoryEntity rce2 = new RecordHistoryEntity();

					try {
						rce2 = recordHisRepo.save(rhe2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				UpdateBudgetDTO updateBud = new UpdateBudgetDTO();
				updateBud.setCategory(records.getCategory());
				updateBud.setSpent(records.getAmount());
				updateBud.setMonth(records.getDate().getMonth());
				rf.updateSpent(updateBud);
			}
		} else {
			throw new InvalidRequestData("The request data is not valid", "404");
		}
		return "updated";

	}

	public List<RecordList> getRecordHistory(String month, String year) {
		List<RecordList> response = new ArrayList<>();

		String monthYear = month + "-" + year;
		log.info("The month and year is-->" + monthYear);

		List<RecordEntity> recordList = new ArrayList();

		// recordList = recordRepo.findByMonthYear(monthYear);

		// log.info("The output record --> " + recordList);

		List<RecordHistoryEntity> rheList = new ArrayList<>();

		rheList = recordHisRepo.findByMonthYear(monthYear);

		for (RecordHistoryEntity rhe : rheList) {
			RecordList rl = new RecordList();
			LocalDate resDate = rhe.getDate();
			String recordsId = rhe.getRecordIds();

			String[] idsArr = recordsId.split("##");
			List<String> idsList = Arrays.asList(idsArr);

			rl.setDate(resDate);
			List<RecordHistoryDto> details = new ArrayList<>();
			for (String ids : idsList) {

				RecordHistoryDto rhd = new RecordHistoryDto();

				RecordEntity re = new RecordEntity();
				re = recordRepo.findById(Integer.parseInt(ids)).get();

				rhd.setAccount(re.getAccount());
				rhd.setCategory(re.getCategory());
				rhd.setAmount(re.getAmount());
				details.add(rhd);
			}
			rl.setDetails(details);
			response.add(rl);
		}

		return response;
	}

	private boolean validateRecordsRequest(RecordEntity records) throws InvalidRequestData {
		if (!(records.getType()).equals("I") && !(records.getType()).equals("E")) {
			log.info("Inside recordType check where type is " + records.getType());
			return false;
		}
		if (!(records.getAccount()).equals("CD") && !(records.getAccount()).equals("CH")
				&& !(records.getAccount()).equals("SV")) {
			log.info("Inside recordAccount check account type is " + records.getAccount());
			return false;
		}

		CategoryEntity response = new CategoryEntity();

		try {
			response = rf.findCategoryDetails(records.getCategory());
		} catch (InvalidRequestData e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Async
	public CompletableFuture<String> addRecordFromFile(MultipartFile file) throws IOException, CsvException {
		Reader reader = new InputStreamReader(file.getInputStream());

		CSVReader csvReader = new CSVReaderBuilder(reader).build();
		List<String[]> rows = csvReader.readAll();
		int count = 0;
		List<RecordEntity> reEntity = new ArrayList<>();
		for (String[] row : rows) {
			count++;

			if (count > 1) {

				RecordEntity records = new RecordEntity();

				records.setType(row[0]);
				records.setAccount(row[1]);
				records.setCategory(row[2]);
				records.setNotes(row[3]);
				records.setAmount(Double.parseDouble(row[4]));

				System.out.println(records);
				reEntity.add(records);
			}

		}

		long st = System.currentTimeMillis();

		reEntity.stream().parallel().forEach(obj -> {
			try {
				log.info("the request data from csv file is --> "+obj.toString());
				addRecord(obj);
			} catch (InvalidRequestData e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		});

		long et = System.currentTimeMillis();

		System.out.println("The total time taken ---> " + (et - st));

		return CompletableFuture.completedFuture("Completed");
	}

}
