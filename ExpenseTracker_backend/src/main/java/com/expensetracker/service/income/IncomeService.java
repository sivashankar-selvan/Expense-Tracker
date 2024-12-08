package com.expensetracker.service.income;

import java.util.List;

import com.expensetracker.dto.IncomeDTO;
import com.expensetracker.entity.Income;


public interface IncomeService {
	
	Income postIncome(IncomeDTO incomeDTO);	
	
	List<IncomeDTO> getAllIncomes();
	
	Income updateIncome(Long id, IncomeDTO incomeDTO);
	
	IncomeDTO getIncomeById(Long id);
	
	void deleteIncome(Long id);
	
	

}