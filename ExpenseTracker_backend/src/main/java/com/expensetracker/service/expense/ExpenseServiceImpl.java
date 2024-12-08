package com.expensetracker.service.expense;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.repository.ExpenseRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService{
	
	private final ExpenseRepository  expenseRepository;
	

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository=expenseRepository;
	}
	
	
	public Expense postExpense(ExpenseDTO expenseDTO) {
		return saveOrUpdateExpense(new Expense(), expenseDTO);
	}
	
	private Expense saveOrUpdateExpense(Expense expense,ExpenseDTO expenseDTO) {
		expense.setTitle(expenseDTO.getTitle());
		expense.setDate(expenseDTO.getDate());
		expense.setAmount(expenseDTO.getAmount());
		expense.setCategory(expenseDTO.getCategory());
		expense.setDescription(expenseDTO.getDescription());
		
		return expenseRepository.save(expense);
		
		
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepository.findAll().stream().sorted(Comparator.comparing(Expense::getDate).reversed())
				.collect(Collectors.toList());
		
	}
	
	public Expense getExpenseById(Long id) {
		Optional<Expense> optionalExpense=expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}else {
			throw new EntityNotFoundException("Expense is not present with id"+id);
		}
	}
	
	public Expense updateExpense(Long id,ExpenseDTO expenseDTO) {
		Optional<Expense> optinalExpense=expenseRepository.findById(id);
		if(optinalExpense.isPresent()) {
			return saveOrUpdateExpense(optinalExpense.get(), expenseDTO);
			
		}
		else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
	
	public void deleteExpense(Long id) {
	    Optional<Expense> optionalExpense = expenseRepository.findById(id);
	    if (optionalExpense.isPresent()) {
	        expenseRepository.deleteById(id);
	    } else {
	        throw new EntityNotFoundException("Expense is not present with id " + id);
	    }
	}


}
