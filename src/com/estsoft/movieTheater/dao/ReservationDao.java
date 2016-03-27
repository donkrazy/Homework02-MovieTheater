package com.estsoft.movieTheater.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.movieTheater.vo.MovieVO;
import com.estsoft.movieTheater.vo.ReservationVO;;

public class ReservationDao {

    public void insert(int id_person, int id_movie, int seats) {
        ReservationVO reservationVO = new ReservationVO();
        ReservationDao reservationDao = new ReservationDao();
        reservationVO.setId_movie(id_movie);
        reservationVO.setId_person(id_person);
        reservationVO.setSeats(seats);
        reservationDao.insert(reservationVO);
    }

    public void insert(ReservationVO reservationVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Utils.getConnection();
            //3. Statement 준비
            String sql = "insert into reservation values( null, ?, ?, ? )";
            pstmt = conn.prepareStatement(sql);
            //4. bind
            pstmt.setInt(1, reservationVO.getSeats());
            pstmt.setInt(2, reservationVO.getId_person());
            pstmt.setInt(3, reservationVO.getId_movie());
            //잔여 좌석 count--
            MovieDao movieDao = new MovieDao();
            MovieVO movieVO = movieDao.get(reservationVO.getId_movie());
            movieVO.setSeats_left(movieVO.getSeats_left() - reservationVO.getSeats());
            movieDao.updateState(movieVO);
            //5. SQL 실행
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL 오류:" + ex);
        } finally {
            //6. 자원정리(clean-up)
            Utils.clean_up(conn, pstmt);
        }
    }

    public ReservationVO get(int no) {
        ReservationVO reservationVO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            //3. Statement 준비
            String sql = "SELECT a.id FROM reservation a where a.id=?";
            pstmt = conn.prepareStatement(sql);
            //4. bind
            pstmt.setInt(1, no);
            //5. SQL 실행
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int id_person = rs.getInt(2);
                int id_movie = rs.getInt(3);
                int seats = rs.getInt(4);
                reservationVO = new ReservationVO();
                reservationVO.setId(id);
                reservationVO.setId_person(id_person);
                reservationVO.setId_movie(id_movie);
                reservationVO.setSeats(seats);
            }
        } catch (SQLException ex) {
            System.out.println("SQL 오류:" + ex);
        } finally {
            Utils.clean_up(conn, pstmt, rs);
        }
        return reservationVO;
    }

    public void printReservationListByPerson_id(int person_id) {
        List<ReservationVO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            String sql = "SELECT m.title, a.seats FROM reservation a, movie m " +
                        "WHERE a.id_person = ? AND m.id=a.id_movie ORDER BY a.id ASC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, person_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                int seats = rs.getInt(2);
                System.out.println(title + ": " +  seats + "석");
            }
        } catch (SQLException ex) {
            System.out.println("error : " + ex);
        } finally {
            Utils.clean_up(conn, pstmt, rs);
        }
    }
}
