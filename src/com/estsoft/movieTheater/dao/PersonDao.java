package com.estsoft.movieTheater.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.estsoft.movieTheater.vo.PersonVO;

public class PersonDao {

    public PersonVO get(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();

            //3. Statement 준비
            String sql = "SELECT * FROM person a where a.id=?";
            pstmt = conn.prepareStatement(sql);

            //4. bind
            pstmt.setInt(1, id);

            //5. SQL 실행
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt(1);
                String phone_num = rs.getString(2);
                PersonVO personVO = new PersonVO();
                personVO.setId(no);
                personVO.setPhone_num(phone_num);
                return personVO;
            }
        } catch (SQLException ex) {
            System.out.println("SQL 오류:" + ex);
        } finally {
            Utils.clean_up(conn, pstmt, rs);
        }
        return null;
    }


    public void insert(String phone_num) {
        if (getIdByPhone_num(phone_num) != 0) {
        } else if (getIdByPhone_num(phone_num) == 0) {
            PersonVO personVO = new PersonVO();
            PersonDao personDao = new PersonDao();
            personVO.setPhone_num(phone_num);
            personDao.insert(personVO);
        }
    }

    public void insert(PersonVO personVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Utils.getConnection();
            //3. Statement 준비
            String sql = "insert into person values( null, ? )";
            pstmt = conn.prepareStatement(sql);
            //4. bind
            pstmt.setString(1, personVO.getPhone_num());
            //5. SQL 실행
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL 오류:" + ex);
        } finally {
            //6. 자원정리(clean-up)
            Utils.clean_up(conn, pstmt);
        }
    }

    public int getIdByPhone_num(String phone_num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            //3. Statement 준비
            String sql = "SELECT a.id FROM person a where a.phone_num=?";
            pstmt = conn.prepareStatement(sql);
            //4. bind
            pstmt.setString(1, phone_num);
            //5. SQL 실행
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt(1);
                return no;
            }
        } catch (SQLException ex) {
            System.out.println("SQL 오류:" + ex);
        } finally {
            Utils.clean_up(conn, pstmt, rs);
        }
        return 0;
    }
}
