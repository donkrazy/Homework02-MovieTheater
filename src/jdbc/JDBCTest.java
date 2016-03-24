package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. 드라이버 로드
			Class.forName( "com.mysql.jdbc.Driver" );

			//2. Connection 얻기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. SQL 실행
			String sql = 
				" select name, " +
				"         owner, " +
				"         species," +
				"         gender," +
				"         DATE_FORMAT(birth, '%Y-%m-%d')," + 
				"         DATE_FORMAT(death, '%Y-%m-%d')" + 
				"  from pet";
			
			rs = stmt.executeQuery( sql );
			
			// 5. 데이터 받아오기 
			while( rs.next() ) {
				String name = rs.getString( 1 );
				String owner = rs.getString( 2 );
				String species = rs.getString( 3 );
				String gender = rs.getString( 4 );
				String birth = rs.getString( 5 );
				String death = rs.getString( 6 );
				
				System.out.println( name + ":" + owner + ":" + species + ":" + gender + ":" + birth + ":" + death  );
			}
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
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
	}

}
