package _52_Question.controls;

import _52_Question.dao.MemberDao;
import _52_Question.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/authQController/login")
public class LoginController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if(model.get("loginInfo") == null) { // 입력폼을 요청할 때
            return "/auth/LoginForm.jsp";
        } else { // 회원등록을 요청할 때
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member loginInfo = (Member)model.get("loginInfo");

            Member member = memberDao.exist(
                    loginInfo.getEmail(),
                    loginInfo.getPassword()
            );

            if(member != null){
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../memberQController/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
