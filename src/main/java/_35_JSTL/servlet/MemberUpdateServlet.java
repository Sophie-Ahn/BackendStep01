package _35_JSTL.servlet;

import _35_JSTL.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
@WebServlet("/memberjstl/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "SELECT mno, email, mname,cre_date FROM members" +
                            " WHERE mno=" + req.getParameter("no")
            );
            if (rs.next()){
                req.setAttribute("member",
                        new Member().setNo(rs.getInt("mno"))
                                .setEmail(rs.getString("email"))
                                .setName(rs.getString("mname"))
                                .setCreateDate(rs.getDate("cre_date"))
                );
            } else {
                throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
            }


            RequestDispatcher rd = req.getRequestDispatcher(
                    "/member/MemberUpdateForm.jsp"
            );
            rd.forward(req, resp);
        } catch (Exception e){
            // doGet은 톰캣이 호출하는 함수라서 결국엔 톰캣한테 가기 때문에 예외도 톰캣한테 보냄
            throw new ServletException(e);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
//            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // CharacterEncodingFilter로 전처리 했으므로 이제 안해도 됨
//        req.setCharacterEncoding("UTF-8");

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            pstmt = conn.prepareStatement(
                    "UPDATE members SET email = ?, mname = ?, mod_date = NOW()" +
                            " WHERE mno = ?"
            );
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("name"));
            pstmt.setInt(3, Integer.parseInt(req.getParameter("no")));
            pstmt.executeUpdate();

            // list로 화면이 이동
            resp.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
//            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
