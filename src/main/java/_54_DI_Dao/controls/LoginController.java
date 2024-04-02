package _54_DI_Dao.controls;

import _54_DI_Dao.dao.MemberDao;
import _54_DI_Dao.vo.Member;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/authDi/login")
public class LoginController implements Controller {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if(model.get("loginInfo") == null) { // 입력폼을 요청할 때
            return "/auth/LoginForm.jsp";
        } else { // 회원등록을 요청할 때
//            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member loginInfo = (Member)model.get("loginInfo");

            Member member = memberDao.exist(
                    loginInfo.getEmail(),
                    loginInfo.getPassword()
            );

            if(member != null){
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../memberDi/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
