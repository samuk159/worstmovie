package com.samuk159.worstmovie.dto;

import java.util.LinkedList;
import java.util.List;

public class PrizeIntervalDTO {

	private List<PrizeIntervalRow> min;
	private List<PrizeIntervalRow> max;
	
	public List<PrizeIntervalRow> getMin() {
		if (min == null) {
			min = new LinkedList<>();
		}
		
		return min;
	}
	public void setMin(List<PrizeIntervalRow> min) {
		this.min = min;
	}
	public List<PrizeIntervalRow> getMax() {
		if (max == null) {
			max = new LinkedList<>();
		}
		
		return max;
	}
	public void setMax(List<PrizeIntervalRow> max) {
		this.max = max;
	}
	
}
