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
import java.sql.Statement;

@SuppressWarnings("serial")
@WebServlet("/memberDaoQuestion/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            memberDao.delete(Integer.parseInt(req.getParameter("no")));

            resp.sendRedirect("list");
        } catch (Exception e){
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        } finally {
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
        }
    }
}
