package com.estsoft.movieTheater.dao.test;

import com.estsoft.movieTheater.dao.ReservationDao;
import com.estsoft.movieTheater.vo.ReservationVO;

public class ReservationDaoTest {

	public static void main(String[] args) {
		//1. insert test
		insertTest();
	}
	
	public static void insertTest(){
		ReservationVO reservationVO = new ReservationVO();
		ReservationDao reservationDao = new ReservationDao();
		reservationVO.setId_movie(1);
		reservationVO.setId_person(1);
		reservationVO.setSeats(1);
		reservationDao.insert( reservationVO );
	}

}
