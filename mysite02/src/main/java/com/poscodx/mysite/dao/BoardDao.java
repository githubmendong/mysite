package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.GuestBookVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.sql.DriverManager.getConnection;

public class BoardDao {

    // DB Connection
    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            //			Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.182:3307/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("드라이버 로딩 실패:" + e);
        }
        return conn;
    }



    // insert
    public void insert(GuestBookVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "insert into guestbook(name, password, contents, reg_date) " + "values(?, ?, ?, now())";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getContents());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            }
        }
    }
}
