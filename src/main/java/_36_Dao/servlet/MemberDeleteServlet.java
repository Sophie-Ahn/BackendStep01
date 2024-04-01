package _36_Dao.servlet;

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
@WebServlet("/memberdao/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "DELETE FROM members" +
                            " WHERE mno=" + req.getParameter("no")
            );

            resp.sendRedirect("list");
        } catch (Exception e){
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
//            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
