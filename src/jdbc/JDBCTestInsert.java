package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestInsert {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			//1. 드라이버 로드
			Class.forName( "com.mysql.jdbc.Driver" );

			//2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. SQL 실행
			String sql = "insert into pet values( '마음이', '찬이', 'dog', 'm', '2006-09-10', '0000-000-00')";
			stmt.executeUpdate( sql );
			
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			try {
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
	}

}
