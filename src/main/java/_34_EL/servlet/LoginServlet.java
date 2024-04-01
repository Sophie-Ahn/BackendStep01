package _34_EL.servlet;

import _34_EL.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
@WebServlet("/authel/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            pstmt = conn.prepareStatement(
                    "SELECT mname, email FROM members WHERE email = ? AND pwd = ?"
            );
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("password"));
            rs = pstmt.executeQuery();
            // 회원이 존재하면
            if (rs.next()){
                Member member = new Member()
                        .setEmail(rs.getString("email"))
                        .setName(rs.getString("mname"));
                // 세션 영역에서 로그인 저장
                HttpSession session = req.getSession();
                session.setAttribute("member", member);

                resp.sendRedirect("../memberel/list");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginFail.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {

        } finally {
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
        }
    }
}
