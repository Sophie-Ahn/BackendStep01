package _45_DispatchServlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/authDispatch/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 저장소에 보관한 회원정보(로그인 정보)를 삭제한다.
        HttpSession session = req.getSession();
        session.removeAttribute("member"); // 세션 삭제
        session.invalidate(); // 세션 저장소 자체를 초기화

        resp.sendRedirect("login");
    }
}
