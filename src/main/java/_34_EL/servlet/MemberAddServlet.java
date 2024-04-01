package _34_EL.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/memberel/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(
                "/member/MemberForm.jsp"
        );
        resp.setContentType("text/html;charset=UTF-8");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");
            pstmt = conn.prepareStatement(
                    "INSERT INTO members(email, pwd, mname, cre_date,mod_date)" +" VALUES (?, ?, ?, NOW(), NOW())"
            );
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("password"));
            pstmt.setString(3, req.getParameter("name"));
            pstmt.executeUpdate();

            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>회원등록결과</title></head>");
            out.println("<body>");
            out.println("<p>회원등록 성공입니다</p>");
            out.println("</body></html>");

            resp.sendRedirect("list");
        }catch (Exception e){
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }finally {

            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
        }
    }
}
