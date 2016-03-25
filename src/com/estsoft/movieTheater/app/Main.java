package com.estsoft.movieTheater.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.estsoft.movieTheater.dao.MovieDao;
import com.estsoft.movieTheater.dao.PersonDao;
import com.estsoft.movieTheater.dao.ReservationDao;
import com.estsoft.movieTheater.vo.MovieVO;

public class Main {
	
	static BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
	
	public static void main(String[] args) {		
		while(true)	{
			try	{
				System.out.print(
				"\n== Ticketing System ==\n"+
				"1. 예매 \n"+
				"2. 예매취소\n"+
				"3. 영화/잔여좌석수 확인\n"+
				"4. 종료\n"+
				"> " );
				
				String kind = br.readLine();
				
				switch( Integer.parseInt( kind ) ) {
					case 1: 
						doReserve(); 
						break;
					case 2: 
						//doCancel(); 
						break;
					case 3: 
						doCount(); 
						break;
					case 4:
						System.exit(0);
						break;
					default:
						System.out.println("\n Wrong.\n\n");
				}
			} catch( IOException ie ) {
				System.out.println("Incorrect input.");
			}
		}
	}
	

	static void printMovieListMenu(String title) {
		System.out.println("========" + title + "=========");
		MovieDao movieDao = new MovieDao();
		List<MovieVO> list = movieDao.getList();
		
		for( MovieVO vo : list ) {
			System.out.println( vo );
		}
	}
	
	static void doReserve() throws IOException {
		printMovieListMenu("RESERVATION");
		String no = br.readLine();
		if(Integer.parseInt(no) <= 3) {
			System.out.println("Phone: ");
			String phone_num = br.readLine();
			System.out.println("몇장 예약할래?");
			String seats = br.readLine();
			PersonDao personDao = new PersonDao();
			personDao.insert(phone_num);
			int id_person = personDao.getIdByPhone_num(phone_num);
			new ReservationDao().insert(id_person, Integer.parseInt(no), Integer.parseInt(seats));
		}
	}
	
//	// TODO	
//	static void doCancel() throws IOException {
//		System.out.println("Your name: ");
//		String name = br.readLine();
//		if(ts.cancelReservation(name)){
//			System.out.println("Cancelled.");
//		}
//		else{
//			System.out.println("NOT found");
//		}
//	}
//	

	//	TODO
	static void doCount() throws IOException {
		printMovieListMenu( "Count" );
	}
}
