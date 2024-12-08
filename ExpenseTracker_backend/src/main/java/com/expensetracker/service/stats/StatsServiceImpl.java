package com.expensetracker.service.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.weaving.DefaultContextLoadTimeWeaver;
import org.springframework.stereotype.Service;

import com.expensetracker.dto.GraphDTO;
import com.expensetracker.dto.StatsDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public StatsServiceImpl(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }
    
    public GraphDTO getChartData() {
    	
    	LocalDate enDate=LocalDate.now();
    	LocalDate startDate=enDate.minusDays(27);
    	
    	GraphDTO graphDTO=new GraphDTO();
    	graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, enDate));
    	graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, enDate));
    	
//    	System.out.println(graphDTO);
    	return graphDTO;
    	
    	
    }
    
    public StatsDTO getStats() {
    	
    	Double totalIncome=incomeRepository.sumALlAmounts();
    	Double totalExpense=expenseRepository.sumALlAmounts();
    	
    	Optional<Income> optionalIncome=incomeRepository.findFirstByOrderByDateDesc();
    	Optional<Expense> optionalExpense=expenseRepository.findFirstByOrderByDateDesc();
    	
    	StatsDTO statsDTO=new StatsDTO();
    	statsDTO.setExpese(totalExpense);
    	statsDTO.setIncome(totalIncome);
    	
    	
//    	if(optionalIncome.isPresent()) {
//    		statsDTO.setLatestIncome(optionalIncome.get());
//    	}
    	
    	optionalIncome.ifPresent(statsDTO::setLatestIncome);
    	optionalExpense.ifPresent(statsDTO::setLatestExpense);
    	
    	
    	statsDTO.setBalance(totalIncome-totalExpense);
    	
    	List<Income> incomeList=incomeRepository.findAll();
    	List<Expense> expenseList=expenseRepository.findAll();
    	
    	OptionalDouble minIncome=incomeList.stream().mapToDouble(Income::getAmount).min();
    	OptionalDouble maxIncome=incomeList.stream().mapToDouble(Income::getAmount).max();
    	
    	OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
    	OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();
    	
//    	statsDTO.setMaxExpense(maxExpense.isPresent()? maxExpense.getAsDouble():null);
    	
    	statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
    	statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
    	statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
    	statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
    	
    	return statsDTO;
    }
}
