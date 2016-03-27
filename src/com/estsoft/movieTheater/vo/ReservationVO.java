package com.estsoft.movieTheater.vo;

public class ReservationVO {
	private int id;
	private int id_person;
	private int id_movie;
	private int seats;
	
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_person() {
		return id_person;
	}
	public void setId_person(int id_person) {
		this.id_person = id_person;
	}
	public int getId_movie() {
		return id_movie;
	}
	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}
	@Override
	public String toString() {
		return "ReservationVO [id=" + id + ", id_person=" + id_person + ", id_movie=" + id_movie + ", seats=" + seats
				+ "]";
	}
	public void printInfo(){
        System.out.println(id_movie + "번 영화 ");
    }

}
