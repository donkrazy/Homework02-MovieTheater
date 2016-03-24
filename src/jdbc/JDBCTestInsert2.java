package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTestInsert2 {
	
	public static void main( String[] args ) {
		
		insertPet( "둘리", "안대혁", "cat", "f", "2010-1-1", "0000-0-0" );
		insertPet( "둘리2", "안대혁2", "cat", "m", "2010-1-1", "0000-0-0" );
		insertPet( "둘리3", "안대혁3", "cat", "m", "2010-1-1", "0000-0-0" );
	}
	
	public static void insertPet( String name, String owner, String species, String gender, String birth, String death ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. 드라이버 로드
			Class.forName( "com.mysql.jdbc.Driver" );

			//2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = "insert into pet values(  ?, ?, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setString( 1, name );
			pstmt.setString( 2, owner );
			pstmt.setString( 3, species );
			pstmt.setString( 4, gender );
			pstmt.setString( 5, birth );
			pstmt.setString( 6, death );
			
			//4. SQL 실행
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
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
}
