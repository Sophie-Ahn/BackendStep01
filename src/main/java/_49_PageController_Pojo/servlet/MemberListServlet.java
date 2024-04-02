package _49_PageController_Pojo.servlet;

import _49_PageController_Pojo.dao.MemberDao;
import _49_PageController_Pojo.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/membercontr/list")
public class MemberListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            // Dao로부터 필요한 객체 생성 후 request에 저장
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            List<Member> members = memberDao.selectList();
            req.setAttribute("members", members);

            // jsp이동을 위한 jsp페이지 정보를 request에 저장
            req.setAttribute("viewUrl", "/member/MemberList.jsp");

            // 이제 jsp로 직접 보내는 것이 아니라 DispatchServlet에게 jsp 이동을 맡기다.
//            RequestDispatcher rd = req.getRequestDispatcher(
//                    "/member/MemberList.jsp"
//            );
//            resp.setContentType("text/html;charset=UTF-8");
//            rd.include(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
            // 예외처리를 DispatchServlet에서 처리하도록 일원화되기 위해 아래를 주석처리한다.
//            e.getStackTrace();
//            req.setAttribute("error", e);
//            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//            rd.forward(req, resp);
        }
    }
}
