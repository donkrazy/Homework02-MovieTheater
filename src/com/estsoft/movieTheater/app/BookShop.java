package com.estsoft.movieTheater.app;

import java.util.Scanner;

import com.estsoft.movieTheater.dao.MovieDao;
import com.estsoft.movieTheater.vo.MovieVO;

public class BookShop {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		
		System.out.print( "" );
		int id = scanner.nextInt();
		
		MovieVO movieVO = new MovieVO();
		movieVO.setId( id );
		movieVO.setSeats_left( 1 );
		new MovieDao().updateState( movieVO );
		
		scanner.close();
	}


}
