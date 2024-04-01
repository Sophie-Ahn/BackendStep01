package _41_ConnectionPool.servlet;

import _41_ConnectionPool.dao.MemberDao;
import _41_ConnectionPool.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/memberConnPool/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = memberDao.selectOne(
                    Integer.parseInt(req.getParameter("no")));
            req.setAttribute("member", member);

            RequestDispatcher rd = req.getRequestDispatcher(
                    "/member/MemberUpdateForm.jsp"
            );
            rd.forward(req, resp);
        } catch (Exception e){
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            memberDao.update(new Member()
                    .setNo(Integer.parseInt(req.getParameter("no")))
                    .setName(req.getParameter("name"))
                    .setEmail(req.getParameter("email")));

            // list로 화면이 이동
            resp.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
