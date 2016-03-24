package com.estsoft.movieTheater.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.estsoft.movieTheater.vo.MovieVO;
import com.estsoft.movieTheater.vo.PersonVO;

public class PersonDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로드
			Class.forName( "com.mysql.jdbc.Driver" );

			//2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		} 
		
		return conn;
	}
	
	public void insert(String phone_num){
		PersonVO personVO = new PersonVO();
		
		PersonDao personDao = new PersonDao();
		personVO.setPhone_num( phone_num );
		personDao.insert( personVO );
	}
	
	public void insert( PersonVO personVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into person values(  null, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setString( 1, personVO.getPhone_num() );
			
			//5. SQL 실행
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			//6. 자원정리(clean-up)
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}		
	}
	
	public int getIdByPhone_num( String phone_num ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM person a" +
					" where a.phone_num=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setString( 1, phone_num );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				return no;
			}
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			//6. 자원정리(clean-up)
			try {
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		return 0;
	}
}
