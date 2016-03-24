package com.estsoft.movieTheater.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.movieTheater.vo.MovieVO;

public class MovieDao {
	
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
	
	public void updateState( MovieVO movieVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE movie SET seats_left = ? WHERE no = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, movieVO.getSeats_left() );
			pstmt.setInt( 2, movieVO.getId() );
			pstmt.executeUpdate();
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
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
	
	public void insert( MovieVO movieVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into movie values(  null, ?, 200 )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setString( 1, movieVO.getTitle() );
			
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
	
	public MovieVO get(  int id ) {
		MovieVO movieVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM movie a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				String title = rs.getString( 2 );
				int seats_left = rs.getInt( 3 );
				movieVO = new MovieVO();
				movieVO.setId( no );
				movieVO.setTitle( title );
				movieVO.setSeats_left( seats_left );
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
		
		return movieVO;
	}
	
	public List<MovieVO> getList() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT a.id, a.title, a.seats_left" +
					"       FROM movie a" +
                    " ORDER BY a.id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int id = rs.getInt( 1 );
				String title = rs.getString( 2 );
				int seats_left = rs.getInt( 3 );
				
				MovieVO movieVO = new MovieVO();
				movieVO.setId(id);
				movieVO.setTitle(title);
				movieVO.setSeats_left(seats_left);
				
				list.add( movieVO );
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
