package _45_DispatchServlet.servlet;

import _45_DispatchServlet.dao.MemberDao;
import _45_DispatchServlet.vo.Member;

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
@WebServlet("/memberDispatch/list")
public class MemberListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            List<Member> members = memberDao.selectList();

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
