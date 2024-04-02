package _52_Question.dao;

import _52_Question.vo.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* Dao(Data Access Object)
* 데이터베이스는 연결하여 데이터를 입출력을 담당하는 클래스
* 이 클래스로 만들어진 오브젝트를 Dao라고 부른다.
*/
public class MemberDao {
    private String strSelectList = "SELECT mno, mname, email,cre_date FROM members ORDER BY mno ASC";
    private String strInsert = "INSERT INTO members(email, pwd,mname, cre_date,mod_date) VALUES(?,?,?,NOW(),NOW())";
    private String strDelete = "DELETE FROM members WHERE mno = ?";
    private String strSelectOne = "SELECT mno, email, mname, cre_date FROM members WHERE mno = ?";
    private String strUpdate = "UPDATE members SET email = ?, mname = ?, mod_date = NOW() WHERE mno=?";
    private String strExist = "SELECT mname, email FROM members WHERE email = ? AND pwd = ?";

    // 우리가 만든 커넥션풀을 사용하지 않는다.
//    DBConnectionPool connPool;
//    public void setDBConnectionPool(DBConnectionPool connPool){
//        this.connPool = connPool;
//    }

    // apache 라이브러리에서 제공하는 DataSource내에 커넥션풀이 포함되어 있다.
    DataSource ds;
    public void setDataSource(DataSource ds){
        this.ds = ds;
    }

    public List<Member> selectList() throws Exception {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strSelectList);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while (rs.next()){
                members.add(new Member().setNo(rs.getInt("mno"))
                        .setName(rs.getString("mname"))
                        .setEmail(rs.getString("email"))
                        .setCreateDate(rs.getDate("cre_date"))
                );
            }

            return members;
        }catch (Exception e) {
            throw e;
        } finally {
            try{if(rs != null) rs.close();}catch (Exception e){}
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}
//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }

            // DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라 커넥션풀로의 반환을 의미한다.
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }

        }
    }

    // MemberAddServlet 서블릿에서 필요
    public int insert(Member member) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strInsert);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());

            // DB에서 이 명령어 적용된 row의 개수를 반환한다.
            // 1개 입력되면 1을 리턴한다.
            // 1이면 입력 성공,
            // 0이면 입력 실패

            int count = pstmt.executeUpdate();

            return count;
        }catch (Exception e) {
            throw e;
        } finally {
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}

//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    // MemberDeleteServlet 서블릿에서 필요
    public int delete(int no) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strDelete);
            pstmt.setInt(1, no);

            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}
//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    // MemberUpdateServlet에서 get요청시 필요
    public Member selectOne(int no) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strSelectOne);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return new Member()
                        .setNo(rs.getInt("mno"))
                        .setEmail(rs.getString("email"))
                        .setName(rs.getString("mname"))
                        .setCreateDate(rs.getDate("cre_date"));
            } else {
                throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try{if(rs != null) rs.close();}catch (Exception e){}
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}
//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    // MemberUpdateServlet에서 post요청시 필요
    public int update(Member member) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strUpdate);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getNo());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}
//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    // LogInServlet에서 필요
    public Member exist(String email, String password) throws Exception{
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 커넥션풀에서 객체를 빌려온다.
//            connection = this.connPool.getConnection(); // 추가
            connection = this.ds.getConnection();

            pstmt = connection.prepareStatement(strExist);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()){
                return new Member().setName(rs.getString("mname"))
                        .setEmail(rs.getString("email"));
            } else {
                return null;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try{if(rs != null) rs.close();}catch (Exception e){}
            try{if(pstmt != null) pstmt.close();}catch (Exception e){}
//            if(connection != null){
//                // 커넥션풀에서 빌려온 Connection 객체를 반납한다.
//                connPool.returnConnection(connection);
//            }
            try {if(connection != null) connection.close();} catch(Exception e) {}
        }
    }
}
