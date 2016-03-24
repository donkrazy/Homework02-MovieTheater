package com.estsoft.movieTheater.dao.test;

import com.estsoft.movieTheater.dao.PersonDao;
import com.estsoft.movieTheater.vo.PersonVO;

public class PersonDaoTest {

	public static void main(String[] args) {
		//1. insert test
		insertTest();
		
	}

	public static void insertTest(){
		PersonVO personVO = new PersonVO();
		
		PersonDao personDao = new PersonDao();

		personVO.setPhone_num( "010-1531-5451" );
		personDao.insert( personVO );
	}
}
