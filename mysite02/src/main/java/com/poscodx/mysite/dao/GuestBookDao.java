package com.poscodx.mysite.dao;


import com.poscodx.mysite.vo.GuestBookVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class GuestBookDao {
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

	// delete
	public boolean delete(Long no, String password) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			int count = pstmt.executeUpdate();

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

	// insert
	public boolean checkPassword(long no, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "select if(no=? and password=?, true, false) " + "from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			pstmt.setLong(3, no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					result = true;
				}
			}

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
		return result;
	}

	// select
	public List<GuestBookVo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> result = new ArrayList<GuestBookVo>();

		try {
			conn = getConnection();

			String sql = "select no, name, contents, reg_date from guestbook order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = (long) rs.getInt(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);

				result.add(vo);
			}

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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
		}
		return result;
	}
}