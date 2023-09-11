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

    public void update(UserVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            if("".equals(vo.getPassword())) {
                String sql = "update user set name=?, gender=? where no=?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getGender());
                pstmt.setLong(3, vo.getNo());
            } else {
                String sql = "update user set name=?, gender=?, password=password(?) where no=?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getGender());
                pstmt.setString(3, vo.getPassword());
                pstmt.setLong(4, vo.getNo());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
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

    public UserVo findByEmailAndPassword(String email, String password) {
        UserVo userVo = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql =
                    "select no, name" +
                            "  from user" +
                            " where email=?" +
                            "   and password=password(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            //5. 결과 처리
            if(rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);

                userVo = new UserVo();
                userVo.setNo(no);
                userVo.setName(name);
            }

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
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

        return userVo;
    }


}
