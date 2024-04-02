package _46_MemberListServlet_To_PageController.servlet;

import _46_MemberListServlet_To_PageController.dao.MemberDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/memberPageCont/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection conn = null;
//        Statement stmt = null;

        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            memberDao.delete(Integer.parseInt(req.getParameter("no")));
//            conn = (Connection)sc.getAttribute("conn");
//            stmt = conn.createStatement();
//            stmt.executeUpdate(
//                    "DELETE FROM members" +
//                            " WHERE mno=" + req.getParameter("no")
//            );

            resp.sendRedirect("list");
        } catch (Exception e){
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
