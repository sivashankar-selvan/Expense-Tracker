package com.expensetracker.dto;

import java.util.List;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
public class GraphDTO {
	
	@JsonManagedReference
	private List<Expense> expenseList;
	
	@JsonManagedReference
	private List<Income> incomeList;
	
	public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Expense> getExpenseList() {
		return expenseList;
	}

	public List<Income> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }


}
