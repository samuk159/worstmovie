package com.samuk159.worstmovie.dto;

public class PrizeIntervalRow {

	private String producer;
	private Long interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
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
	
}
