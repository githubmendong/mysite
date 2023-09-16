package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestBookVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




//select * from board order by group_no desc, u_no asc
public class BoardDao {

    // DB Connection
    private static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            //			Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.182:3307/webdb?charset=utf8";
//            String url = "jdbc:mariadb://192.168.219.154:3307/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("드라이버 로딩 실패:" + e);
        }
        return conn;
    }


    public List<BoardVo> findAll(int start, String search) {
        List<BoardVo> result = new ArrayList<BoardVo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql =
                    "select no, title, contents, hit, reg_date, g_no, o_no, depth, user_no "
                            + "from board "
                            + "where title like ? "
                            + "order by g_no desc, o_no asc "
                            + "limit ?, 5";
            pstmt = conn.prepareStatement(sql);

            String s = "%"+search+"%";

            pstmt.setString(1, s);
            pstmt.setInt(2, start);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String contents = rs.getString(3);
                int hit = rs.getInt(4);
                String regDate = rs.getString(5);
                int groupNo = rs.getInt(6);
                int orderNo = rs.getInt(7);
                int depth = rs.getInt(8);
                Long userNo = rs.getLong(9);

                String userName = new UserDao().findByNo(userNo).getName();

                BoardVo vo = new BoardVo();
                vo.setNo(no);
                vo.setTitle(title);
                vo.setContents(contents);
                vo.setHit(hit);
                vo.setRegDate(regDate);
                vo.setgNo(groupNo);
                vo.setoNo(orderNo);
                vo.setDepth(depth);
                vo.setUserNo(userNo);
                vo.setUserName(userName);

                result.add(vo);
            }

        } catch (SQLException e) {
            System.out.println("Board Select error: " + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // write
    public Boolean write(BoardVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println("Title : "+vo.getTitle());
        System.out.println("Contents : "+vo.getContents());
        boolean result = false;

        try {
            conn = getConnection();
            String sql = " insert into board(no, title, contents, reg_date, hit, g_no, o_no, depth, user_no) "
                    + "values(null, ?, ?, sysdate(), ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setInt(3, vo.getHit());
            pstmt.setInt(4, vo.getgNo());
            pstmt.setInt(5, vo.getoNo());
            pstmt.setInt(6, vo.getDepth());
            pstmt.setLong(7, vo.getUserNo());
            int count = pstmt.executeUpdate();
            result = count == 1;
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 자원정리(clean-up)
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean updateBoard(BoardVo vo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql =
                    "update board "
                            + "set title=?, contents=? "
                            + "where no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setLong(3, vo.getNo());

            int cnt = pstmt.executeUpdate();
            result = cnt == 1;

        } catch (SQLException e) {
            System.out.println("Board Update error: " + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public boolean updateOrderNo(int groupNo, int orderNo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql =
                    "update board "
                            + "set o_no = o_no+1 "
                            + "where g_no=? "
                            + "and o_no > ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupNo);
            pstmt.setInt(2, orderNo);

            int cnt = pstmt.executeUpdate();
            result = cnt >= 1;

        } catch (SQLException e) {
            System.out.println("Board OrderNo Update error: " + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // write
    public Boolean reply(BoardVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = getConnection();
            String sql = " insert into board(no, title, contents, reg_date, hit, g_no, o_no, depth, user_no) "
                    + "values(null, ?, ?, sysdate(), ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setInt(3, vo.getHit());
            pstmt.setInt(4, vo.getgNo());
            pstmt.setInt(5, vo.getoNo());
            pstmt.setInt(6, vo.getDepth());
            pstmt.setLong(7, vo.getUserNo());
            int count = pstmt.executeUpdate();
            result = count == 1;
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 자원정리(clean-up)
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
//        boolean result = false;
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = getConnection();
//            String sql = "INSERT INTO board(title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
//                    + "SELECT ?, ?, 0, NOW(), ? , ? , ? , ? "
//                    + "FROM board "
//                    + "WHERE g_no = (SELECT g_no FROM board WHERE no = ?) "
//                    + "GROUP BY g_no";
//
//
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setString(1, vo.getTitle());
//            pstmt.setString(2, vo.getContents());
//            pstmt.setLong(3, vo.getGroupNo());
//            pstmt.setLong(4, vo.getOrderNo());
//            pstmt.setLong(5, vo.getDepth());
//            pstmt.setLong(6, vo.getUserNo());
//            pstmt.setLong(7, vo.getUserNo());
//
//            int count = pstmt.executeUpdate();
//
//            result = count == 1;
//
//        } catch (SQLException e) {
//            System.out.println("Error:" + e);
//        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return result;
    }


    public static int findHasSameDepth(int gNo, int depth) {
        int countSameDepth=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            String sql = "select count(*) from board where g_no=? and depth=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, gNo);
            pstmt.setLong(2, depth);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                countSameDepth = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("error " + e);
            }
        }
        return countSameDepth;
    }

    public int findAllCount() {
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select count(*) from board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public int getMaxGroup() {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql =
                    "select max(g_no) "
                            + "from board ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int groupNo = rs.getInt(1);

                result = groupNo;
            }

        } catch (SQLException e) {
            System.out.println("Board GroupSelect error: " + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public boolean setOrder(int groupNo, int orderNo, int depth) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result=false;
        try {
            conn = getConnection();
            String sql = "update board "
                    + "set o_no=o_no+1 "
                    + "where g_no=? and o_no>?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, groupNo);
            pstmt.setLong(2, orderNo);

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 자원정리(clean-up)
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static int findHasPlus1Depth(int groupNo, int depth) {
        int countPlus1Depth=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "select count(*) from board where g_no=? and depth=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, groupNo);
            pstmt.setLong(2, (depth+1));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                countPlus1Depth = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("error " + e);
            }
        }
        return countPlus1Depth;
    }
    public Boolean deleteByNo(Long no) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            String sql = "delete" + " from board" + " where no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    // update할 정보 불러오기
    public BoardVo findByNo(Long no2) {
        BoardVo vo = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "SELECT a.no, a.title, a.contents, "
                    + "a.reg_date, a.hit, a.g_no, a.o_no, "
                    + "a.depth, b.no, b.name "
                    + "from board a, user b where a.user_no = b.no and a.no=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String regDate = rs.getString(4);
                int hit = rs.getInt(5);
                int groupNo = rs.getInt(6);
                int orderNo = rs.getInt(7);
                int depths = rs.getInt(8);
                Long userNo = rs.getLong(9);
                String userName = rs.getString(10);

                vo = new BoardVo();
                vo.setNo(no);
                vo.setTitle(title);
                vo.setContents(content);
                vo.setRegDate(regDate);
                vo.setHit(hit);
                vo.setgNo(groupNo);
                vo.setoNo(orderNo);
                vo.setDepth(depths);
                vo.setUserNo(userNo);
                vo.setUserName(userName);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vo;
    }

    public BoardVo updateView(Long no, String title, String contents) {
        BoardVo vo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = getConnection();
            String sql = "update board set title = ? , contents = ? where no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, contents);
            pstmt.setLong(3, no);
            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 자원정리(clean-up)
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vo;
    }


    // update
    public boolean update(BoardVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();

            // 3. statement 준비
            String sql = "update board set title = ?, contents = ?, date = now() where no = ?";

            pstmt = conn.prepareStatement(sql); // row값

            // 4. Binding
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setLong(3, vo.getNo());

            // 4. SQL 실행
            int count = pstmt.executeUpdate(); //

            // 5. 결과처리
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }
    public boolean hit(BoardVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();
            String sql =  "update board set hit=hit+1 where no=?";

            pstmt = conn.prepareStatement(sql); // row값
            pstmt.setLong(1, vo.getNo());
            int count = pstmt.executeUpdate(); //

            result = count == 1;

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public void updateHit(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = getConnection();
            String sql = "update board set hit = hit + 1 where no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


        public List<BoardVo> findAllSearch(String searchValue, int first, int second) {
            List<BoardVo> result = new ArrayList<>();

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = getConnection();
                String sql = " select a.no ,  a.title  , "
                        + "a.contents , a.reg_date, a.depth , a.hit , "
                        + "b.no as user_no , b.name "
                        + "from board a ,user b "
                        + "where a.user_no = b.no and a.title like '%" + searchValue + "%' "
                        + "order by  a.g_no  desc, a.o_no asc limit ? , ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, first);
                pstmt.setInt(2, second);

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    Long no = rs.getLong(1);
                    String title = rs.getString(2);
                    String contents = rs.getString(3);
                    String regDate = rs.getString(4);
                    int depth = rs.getInt(5);
                    int hit = rs.getInt(6);
                    Long userNo = rs.getLong(7);
                    String userName = rs.getString(8);

                    BoardVo vo = new BoardVo();
                    vo.setNo(no);
                    vo.setTitle(title);
                    vo.setContents(contents);
                    vo.setRegDate(regDate);
                    vo.setDepth(depth);
                    vo.setHit(hit);
                    vo.setUserNo(userNo);
                    vo.setUserName(userName);

                    result.add(vo);
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            } finally {
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

}
