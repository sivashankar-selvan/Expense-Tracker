package com.expensetracker.service.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.dto.IncomeDTO;
import com.expensetracker.entity.Income;
import com.expensetracker.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{
	
	
	private final IncomeRepository incomeRepository;
	
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }
	
	public Income postIncome(IncomeDTO incomeDTO) {
		return saveOrUpdateIncome(new Income(),incomeDTO);
	}
	
	public Income saveOrUpdateIncome(Income income,IncomeDTO incomeDTO) {
		income.setTitle(incomeDTO.getTitle());
		income.setDate(incomeDTO.getDate());
		income.setAmount(incomeDTO.getAmount());
		income.setCategory(incomeDTO.getCategory());
		income.setDescription(incomeDTO.getDescription());
		
		return incomeRepository.save(income);
		
	}
	
	public List<IncomeDTO> getAllIncomes() {
	    return incomeRepository.findAll().stream()
	            .sorted(Comparator.comparing(Income::getDate).reversed())
	            .map(Income::getIncomeDTO)
	            .collect(Collectors.toList());
	}
	
	
	public Income updateIncome(Long id, IncomeDTO incomeDTO) {
		Optional<Income> optionalIncome=incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
		}
		else{
			throw new EntityNotFoundException("Income is not present with id" +id);
		}
	}
	
	public IncomeDTO getIncomeById(Long id) {
		Optional<Income> optionalIncome=incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return optionalIncome.get().getIncomeDTO();
		}else {
			throw new EntityNotFoundException("Income is not present with id "+id);
		}
	}
	
	public void deleteIncome(Long id) {
		Optional<Income> optionalIncome=incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			incomeRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
	
	
	
	
}
