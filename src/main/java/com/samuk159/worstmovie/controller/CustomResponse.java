package com.samuk159.worstmovie.controller;

public class CustomResponse<T> {

	private T data;
	private String message;
	
	public CustomResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomResponse(T data) {
		super();
		this.data = data;
	}
	
	public CustomResponse(String message) {
		super();
		this.message = message;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
