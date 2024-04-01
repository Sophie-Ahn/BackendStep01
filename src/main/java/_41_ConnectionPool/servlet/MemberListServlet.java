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
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/memberConnPool/list")
public class MemberListServlet extends HttpServlet {
//    Connection conn = null;
//    Statement stmt = null;
//    ResultSet rs = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            List<Member> members = memberDao.selectList();

//            conn = (Connection)sc.getAttribute("conn");
//            MemberDao memberDao = new MemberDao();
//            memberDao.setConnection(conn);
//            List<Member> members = memberDao.selectList();

            req.setAttribute("members", members);
            RequestDispatcher rd = req.getRequestDispatcher(
                    "/member/MemberList.jsp"
            );
            resp.setContentType("text/html;charset=UTF-8");
            rd.include(req, resp);
        } catch (Exception e) {
            e.getStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
