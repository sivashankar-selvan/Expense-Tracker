package com.expensetracker.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense,Long>{
	
	List<Expense> findByDateBetween(LocalDate startDate,LocalDate enDate);
	
	@Query("SELECT SUM(e.amount) FROM Expense e")
	Double sumALlAmounts();
	
	Optional<Expense> findFirstByOrderByDateDesc();
	
}
