package _27_MemberListServlet_Including.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SuppressWarnings("serial")
@WebServlet("/memberIncluding/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")
            );
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "DELETE FROM members" +
                            " WHERE mno=" + req.getParameter("no")
            );

            resp.sendRedirect("list");
        } catch (Exception e){
            // doGet은 톰캣이 호출하는 함수라서 결국엔 톰캣한테 가기 때문에 예외도 톰캣한테 보냄
            throw new ServletException(e);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
