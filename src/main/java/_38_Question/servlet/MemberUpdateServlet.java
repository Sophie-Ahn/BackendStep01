package _38_Question.servlet;

import _38_Question.dao.MemberDao;
import _38_Question.vo.Member;

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
@WebServlet("/memberDaoQuestion/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            MemberDao memberDao = new MemberDao();
            stmt = conn.createStatement();

            Member member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
            req.setAttribute("member", member);

            RequestDispatcher rd = req.getRequestDispatcher(
                    "/member/MemberUpdateForm.jsp"
            );
            rd.forward(req, resp);
        } catch (Exception e){
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            memberDao.update(new Member()
                            .setNo(Integer.parseInt(req.getParameter("no")))
                    .setEmail("email")
                    .setName("name"));

            resp.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
        }
    }
}
