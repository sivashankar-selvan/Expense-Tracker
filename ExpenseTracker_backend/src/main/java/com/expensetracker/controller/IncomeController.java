


package com.expensetracker.controller;

import com.expensetracker.dto.*;
import com.expensetracker.entity.Income;
//import com.expensetracker.services.expense.IncomeService;
import com.expensetracker.service.income.IncomeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {

  @Autowired
  private final IncomeService incomeService;
  
  public IncomeController(IncomeService incomeService) {
      this.incomeService = incomeService;
  }
  
  @PostMapping
  public ResponseEntity<?> postIncome(@RequestBody IncomeDTO incomeDTO){
  	Income createdIncome=incomeService.postIncome(incomeDTO);
  	if(createdIncome!=null) {
  		return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
  	}else {
  		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  	}
  }
  
  
  @GetMapping("/all")
  public ResponseEntity<?> getAllIncomes() {
//   	return incomeRepository.findAll().stream().sorted(Comparator.comparing(Income::getIncomedate).reversed()).map(Income::getIncomeDto).collect(Collectors.toList());
  	return ResponseEntity.ok(incomeService.getAllIncomes());
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
      try {
          Income updatedIncome = incomeService.updateIncome(id, incomeDTO);
          return ResponseEntity.ok(updatedIncome);
      } catch (EntityNotFoundException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      }catch (Exception e) {
			// TODO: handle exception
      	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
      try {
			return ResponseEntity.ok(incomeService.getIncomeById(id));
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
  	try {
          incomeService.deleteIncome(id);
          return ResponseEntity.ok(null);
      } catch (EntityNotFoundException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      }catch (Exception e) {
			// TODO: handle exception
      	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
   }
  	
  	


}
