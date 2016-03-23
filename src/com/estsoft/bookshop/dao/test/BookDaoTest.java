package com.estsoft.bookshop.dao.test;

import java.util.List;

import com.estsoft.bookshop.dao.BookDao;
import com.estsoft.bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		//insert test
		//insertTest();
		
		// updateState test
		updateStateTest();
		
		//getList test
		getListTest();
	}
	
	public static void updateStateTest() {
		BookVo bookVo = new BookVo();
		bookVo.setNo( 6L );
		bookVo.setState( "available" );
		new BookDao().updateState( bookVo );
	}
	
	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();
		for( BookVo bookVo : list ) {
			System.out.println( bookVo );
		}
	}
	
	public static void insertTest() {
		BookVo bookVo = new BookVo();
		BookDao bookDao = new BookDao();
		
		bookVo.setTitle( "트와일라잇" );
		bookVo.setAuthorNo( 1L );
		bookDao.insert(bookVo);
		
		bookVo.setTitle( "뉴문" );
		bookVo.setAuthorNo( 1L );
		bookDao.insert(bookVo);

		bookVo.setTitle( "이클립스" );
		bookVo.setAuthorNo( 1L );
		bookDao.insert(bookVo);

		bookVo.setTitle( "브레이킹던" );
		bookVo.setAuthorNo( 1L );
		bookDao.insert(bookVo);

		bookVo.setTitle( "아리랑" );
		bookVo.setAuthorNo( 2L );
		bookDao.insert(bookVo);
		
		bookVo.setTitle( "젊은그들" );
		bookVo.setAuthorNo( 3L );
		bookDao.insert(bookVo);
		
		bookVo.setTitle( "아프니까 청춘이다" );
		bookVo.setAuthorNo( 4L );
		bookDao.insert(bookVo);
		
		bookVo.setTitle( "귀천" );
		bookVo.setAuthorNo( 5L );
		bookDao.insert(bookVo);		
	}
}
