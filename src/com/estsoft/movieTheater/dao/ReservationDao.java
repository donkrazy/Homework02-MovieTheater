package com.estsoft.movieTheater.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.movieTheater.vo.ReservationVO;;

public class ReservationDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로드
			Class.forName( "com.mysql.jdbc.Driver" );

			//2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection( url, "webdb", "webdb" );
			
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		} 
		
		return conn;
	}
	
	public void insert(int id_person, int id_movie, int seats){
		ReservationVO reservationVO = new ReservationVO();
		ReservationDao reservationDao = new ReservationDao();
		reservationVO.setId_movie(id_movie);
		reservationVO.setId_person(id_person);
		reservationVO.setSeats( seats );
		reservationDao.insert( reservationVO );
	}
	
	public void insert( ReservationVO reservationVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into reservation values(  null, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setInt( 1, reservationVO.getId_person() );
			pstmt.setInt( 2, reservationVO.getId_movie() );
			pstmt.setInt( 3, reservationVO.getSeats() );
			
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
	
	public ReservationVO get(  int no ) {
		ReservationVO reservationVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM reservation a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, no );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int id = rs.getInt( 1 );
				int id_person = rs.getInt( 2 );
				int id_movie = rs.getInt( 3 );
				int seats = rs.getInt( 4 );
				reservationVO = new ReservationVO();
				reservationVO.setId( id );
				reservationVO.setId_person( id_person );
				reservationVO.setId_movie( id_movie );
				reservationVO.setSeats( seats );
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
		
		return reservationVO;
	}
	
	public List<ReservationVO> getList() {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT a.id, a.id_person, a.id_movie, a.seats" +
					"       FROM reservation a" +
                    " ORDER BY a.id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int id = rs.getInt( 1 );
				int id_person = rs.getInt( 2 );
				int id_movie = rs.getInt( 3 );
				int seats = rs.getInt( 3 );
				ReservationVO reservationVO = new ReservationVO();
				reservationVO.setId(id);
				reservationVO.setId_person(id_person);
				reservationVO.setId_movie(id_movie);
				reservationVO.setSeats(seats);
				list.add( reservationVO );
			}
			
		} catch( SQLException ex ) {
			System.out.println( "error : " + ex );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		return list;
	}
}
