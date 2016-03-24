package com.estsoft.movieTheater.dao.test;

import java.util.List;

import com.estsoft.movieTheater.dao.MovieDao;
import com.estsoft.movieTheater.vo.MovieVO;

public class MovieDaoTest {

	public static void main(String[] args) {
		//insert test
		insertTest();
		
		// updateState test
		//updateStateTest();
		
		//getList test
		getListTest();
	}
	
	public static void updateStateTest() {
		MovieVO movieVO = new MovieVO();
		movieVO.setId(1);
		movieVO.setSeats_left(20);
		new MovieDao().updateState( movieVO );
	}
	
	public static void getListTest() {
		List<MovieVO> list = new MovieDao().getList();
		for( MovieVO movieVO : list ) {
			System.out.println( movieVO );
		}
	}
	
	public static void insertTest() {
		MovieVO movieVO = new MovieVO();
		MovieDao bookDao = new MovieDao();
		
		movieVO.setTitle( "ddd" );
		bookDao.insert(movieVO);
		
		movieVO.setTitle( "뉴문" );
		bookDao.insert(movieVO);

		movieVO.setTitle( "이클립스" );
		bookDao.insert(movieVO);

	}
}
