package com.estsoft.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookshop.vo.BookVo;

public class BookDao {
	
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
	
	public void updateState( BookVo bookVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "UPDATE book SET state = ? WHERE no = ?";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, bookVo.getState() );
			pstmt.setLong( 2, bookVo.getNo() );
			
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
	
	public void insert( BookVo bookVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into book values(  null, ?, 'available', ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setString( 1, bookVo.getTitle() );
			pstmt.setLong( 2, bookVo.getAuthorNo() );
			
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
	
	public BookVo get(  Long no ) {
		BookVo bookVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.no, a.title, IF( a.state = 'rent', '대여중', '재고있음' ), b.name" +
					"       FROM book a, author b" +
                    "     WHERE a.author_no = b.no" +
					"         AND a.no = ?";
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setLong( 1, no );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				Long no2 = rs.getLong( 1 );
				String title = rs.getString( 2 );
				String state = rs.getString( 3 );
				String authorName = rs.getString( 4 );
				
				bookVo = new BookVo();
				bookVo.setNo( no2 );
				bookVo.setTitle( title );
				bookVo.setState( state );
				bookVo.setAuthorName( authorName );				
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
		
		return bookVo;
	}
	
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT a.no, a.title, IF( a.state = 'rent', '대여중', '재고있음' ), b.name" +
					"       FROM book a, author b" +
                    "     WHERE a.author_no = b.no" +
                    " ORDER BY a.no ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				String state = rs.getString( 3 );
				String authorName = rs.getString( 4 );
				
				BookVo bookVo = new BookVo();
				bookVo.setNo(no);
				bookVo.setTitle(title);
				bookVo.setState(state);
				bookVo.setAuthorName(authorName);
				
				list.add( bookVo );
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
