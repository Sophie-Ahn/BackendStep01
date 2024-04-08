package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;
import _66_Projects_Update.bind.DataBinding;
import _66_Projects_Update.dao.MemberDao;
import _66_Projects_Update.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authpUpdate/login.do")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member loginInfo = (Member)model.get("loginInfo");
        if(loginInfo.getEmail() == null){
            System.out.println("LoginController::execute() - get 요청");
            return "/auth/LoginForm.jsp";
        } else {
            Member member = memberDao.exist(
                    loginInfo.getEmail(),
                    loginInfo.getPassword()
            );

            if(member != null){
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../memberpUpdate/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
