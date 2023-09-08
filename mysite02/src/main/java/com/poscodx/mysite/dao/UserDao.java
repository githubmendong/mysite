package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.mysite.vo.UserVo;

import java.sql.*;

public class UserDao {

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
//			Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.182:3307/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Boolean insert(UserVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql =
                    " insert" +
                            "   into user" +
                            " values(null, ?, ?, password(?), ?, current_date())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getGender());

            int count = pstmt.executeUpdate();

            //5. 결과 처리
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }

                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}
