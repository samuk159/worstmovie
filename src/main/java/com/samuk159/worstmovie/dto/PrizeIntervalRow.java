package com.samuk159.worstmovie.dto;

public class PrizeIntervalRow {

	private String producer;
	private Integer previousWin;
	private Integer followingWin;
	
	public PrizeIntervalRow() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PrizeIntervalRow(String producer) {
		super();
		this.producer = producer;
	}

	public PrizeIntervalRow(String producer, Integer previousWin, Integer followingWin) {
		super();
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public int getInterval() {
		return followingWin - previousWin;
	}
	public Integer getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	public Integer getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public String toString() {
		return "PrizeIntervalRow [producer=" + producer + ", previousWin=" + previousWin + ", followingWin="
				+ followingWin + ", interval=" + getInterval() + "]";
	}
	
}
