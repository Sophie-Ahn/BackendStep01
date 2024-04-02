package _47_MemberList_jsp_link_append_Dot_do.servlet;

import _47_MemberList_jsp_link_append_Dot_do.dao.MemberDao;
import _47_MemberList_jsp_link_append_Dot_do.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/memberlink/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DispatchService에 jsp이동을 맡긴다.
        req.setAttribute("viewUrl", "/member/MemberForm.jsp");

//        RequestDispatcher rd = req.getRequestDispatcher(
//                "/member/MemberForm.jsp"
//        );
//        resp.setContentType("text/html;charset=UTF-8");
//        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            Member member = (Member)req.getAttribute("member");
            memberDao.insert(member);

            req.setAttribute("viewUrl", "redirect:list.do");

//            memberDao.insert(new Member()
//                    .setEmail(req.getParameter("email"))
//                    .setPassword(req.getParameter("password"))
//                    .setName(req.getParameter("name"))
//            );
//
//            resp.sendRedirect("list");
        }catch (Exception e){
            throw new ServletException(e);
//            e.printStackTrace();
//            req.setAttribute("error", e);
//            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//            rd.forward(req, resp);
        }
    }
}
