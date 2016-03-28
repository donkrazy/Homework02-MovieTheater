package com.estsoft.movieTheater.dao;

import java.sql.*;

public class Utils {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            //1. 드라이버 로드
            Class.forName("com.mysql.jdbc.Driver");
            //2. Connection 얻기
            String url = "jdbc:mysql://localhost:3306/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");

        } catch (ClassNotFoundException ex) {
            System.out.println("드라이버를 찾을 수 없습니다:" + ex);
        }
        return conn;
    }

    public static void clean_up(Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null && conn != null) {
                pstmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void clean_up(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void clean_up(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null && stmt != null && conn != null) {
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
