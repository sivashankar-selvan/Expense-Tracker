package com.expensetracker.service.stats;

import com.expensetracker.dto.GraphDTO;
import com.expensetracker.dto.StatsDTO;

public interface StatsService {
	
	GraphDTO getChartData();
	
	StatsDTO getStats();

}
