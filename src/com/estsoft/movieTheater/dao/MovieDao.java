package com.estsoft.movieTheater.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.estsoft.movieTheater.vo.MovieVO;

public class MovieDao {

    public MovieVO get( int id ) {
        MovieVO movieVO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            //3. Statement 준비
            String sql = "SELECT * FROM movie a where a.id=?";
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
            Utils.clean_up(conn, pstmt, rs);
        }
        return movieVO;
    }


	public void updateState( MovieVO movieVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			String sql = "UPDATE movie SET seats_left = ? WHERE id = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, movieVO.getSeats_left() );
			pstmt.setInt( 2, movieVO.getId() );
			pstmt.executeUpdate();
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
		} finally {
            Utils.clean_up(conn, pstmt);
		}	
	}
	
	public void insert( MovieVO movieVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
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
            Utils.clean_up(conn, pstmt);
		}		
	}

	
	public List<MovieVO> getList() {
		List<MovieVO> list = new ArrayList<>();
        int i = 1;
        while(get(i) != null){
            list.add(get(i));
            i += 1;
        }
        // Q: 쿼리 10번 날려서 VO 하나 list에 추가하기 vs 쿼리 한 번 날리고 한줄씩 VO 만들기
		return list;
	}
}
