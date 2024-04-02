package _49_PageController_Pojo.servlet;

import _49_PageController_Pojo.dao.MemberDao;
import _49_PageController_Pojo.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/authcontr/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("viewUrl", "/auth/LoginForm.jsp");

//        RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginForm.jsp");
//        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            Member member = memberDao.exist(req.getParameter("email"), req.getParameter("password"));

//            Member member = memberDao.exist(
//                    req.getParameter("email"),
//                    req.getParameter("password"));
            if (member != null) {
                HttpSession session = req.getSession();
                session.setAttribute("member", member);

                req.setAttribute("viewUrl", "redirect:../membercontr/list.do");
//                resp.sendRedirect("../memberLinkQuestion/list");
            } else {
                req.setAttribute("viewUrl", "/auth/LoginFail.jsp");
//                RequestDispatcher rd = req.getRequestDispatcher(
//                        "/auth/LoginFail.jsp");
//                rd.forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
//            e.printStackTrace();
//            req.setAttribute("error", e);
//            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//            rd.forward(req, resp);
        }
    }
}
