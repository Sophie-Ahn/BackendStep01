package _39_ServletContextListener.servlet;

import _39_ServletContextListener.dao.MemberDao;
import _39_ServletContextListener.vo.Member;

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
@WebServlet("/authListener/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;

        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = memberDao.exist(
                    req.getParameter("email"),
                    req.getParameter("password"));
            if (member != null) {
                HttpSession session = req.getSession();
                session.setAttribute("member", member);
                resp.sendRedirect("../memberListener/list");

            } else {
                RequestDispatcher rd = req.getRequestDispatcher(
                        "/auth/LogInFail.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {

        }
    }
}
