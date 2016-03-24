package com.estsoft.movieTheater.app;

import java.util.ArrayList;
import java.util.List;

public class TicketingSystem {
	
	static List<Reservation> listReservation = new ArrayList<Reservation>();
	
	public void addReservation( int movie_id, String customer_name ) {
		Reservation r = new Reservation( movie_id, customer_name );
		listReservation.add( r ) ;
	}
	
	public boolean cancelReservation( String customer_name ) {
		for (Reservation reservation : listReservation) {
			if(customer_name.equals(reservation.getCustomerName())){
				listReservation.remove(reservation);
				return true;
			}
		}
		return false;
	}
		
	public int reservedCount( int movie_id ) {
		int count=0;
		for( Reservation item: listReservation ) {
			if ( item.getMovieID() == movie_id) {
				count++;
			}
		}	
		return count;
	}
}
