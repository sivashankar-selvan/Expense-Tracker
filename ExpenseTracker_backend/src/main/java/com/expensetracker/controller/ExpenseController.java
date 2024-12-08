package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.service.expense.*;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin("*") 
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@Autowired // Optional, as Spring Boot will inject it automatically with one constructor
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

	@PostMapping
	public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto){
		Expense createdExpense=	expenseService.postExpense(dto);
		if(createdExpense!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllExpense(){
		return ResponseEntity.ok(expenseService.getAllExpenses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getExpenseById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(expenseService.getExpenseById(id));
		}catch (EntityNotFoundException ex) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO dto){
		try {
			return ResponseEntity.ok(expenseService.updateExpense(id, dto));
		}catch (EntityNotFoundException ex) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long id){
		try {
			expenseService.deleteExpense(id);
			return ResponseEntity.ok(null);
		}catch (EntityNotFoundException ex) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	

}
