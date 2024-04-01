package _43_Tomcat_DataSource.servlet;

import _43_Tomcat_DataSource.dao.MemberDao;
import _43_Tomcat_DataSource.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/authTomcatDataSource/login")
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
                resp.sendRedirect("../memberTomcatDataSource/list");

            } else {
                RequestDispatcher rd = req.getRequestDispatcher(
                        "/auth/LoginFail.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
