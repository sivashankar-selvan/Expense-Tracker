package com.expensetracker.dto;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {
	
	private Double income;
	
	private Double expese;
	
	private Income latestIncome;
	
	private Expense latestExpense;
	
	
	private Double balance;
	
	private Double minIncome;
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getMinIncome() {
		return minIncome;
	}

	public void setMinIncome(Double minIncome) {
		this.minIncome = minIncome;
	}

	public Double getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(Double maxIncome) {
		this.maxIncome = maxIncome;
	}

	public Double getMinExpense() {
		return minExpense;
	}

	public void setMinExpense(Double minExpense) {
		this.minExpense = minExpense;
	}

	public Double getMaxExpense() {
		return maxExpense;
	}

	public void setMaxExpense(Double maxExpense) {
		this.maxExpense = maxExpense;
	}

	private Double maxIncome;
	
	private Double minExpense;
	
	private Double maxExpense;
	

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getExpese() {
		return expese;
	}

	public void setExpese(Double expese) {
		this.expese = expese;
	}

	public Income getLatestIncome() {
		return latestIncome;
	}

	public void setLatestIncome(Income latestIncome) {
		this.latestIncome = latestIncome;
	}

	public Expense getLatestExpense() {
		return latestExpense;
	}

	public void setLatestExpense(Expense latestExpense) {
		this.latestExpense = latestExpense;
	}
	
	
	
	

}
