package com.estsoft.movieTheater.app;

public class Reservation {
	private int id;
	private String name;
	
	public Reservation(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getMovieID() { 
		return id; 
	}
	
	public String getCustomerName() { 
		return name; 
	}
}
