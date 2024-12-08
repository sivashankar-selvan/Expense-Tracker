package com.expensetracker.service.expense;


import java.util.List;

import com.expensetracker.dto.*;
import com.expensetracker.entity.*;

public interface ExpenseService {
	
	Expense postExpense(ExpenseDTO expenseDTO);
	
	List<Expense> getAllExpenses();
	
	Expense getExpenseById(Long id);
	
	Expense updateExpense(Long id,ExpenseDTO expenseDTO);
	
	void deleteExpense(Long id);
	
}