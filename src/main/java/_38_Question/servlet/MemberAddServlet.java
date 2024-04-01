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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/memberDaoQuestion/add")
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

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

//            Member member = new Member();
            memberDao.insert(new Member()
                    .setEmail(req.getParameter("email"))
                    .setPassword(req.getParameter("password"))
                    .setName(req.getParameter("name"))
            );

            resp.sendRedirect("list");
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }finally {
            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
        }
    }
}
