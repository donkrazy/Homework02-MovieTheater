package com.estsoft.movieTheater.vo;

public class MovieVO {
	private int id;
	private String title;
	private int seats_left;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSeats_left() {
		return seats_left;
	}
	public void setSeats_left(int seats_left) {
		this.seats_left = seats_left;
	}
	@Override
	public String toString() {
		return id +"/" + title + "/" + seats_left;
	}
	
}
