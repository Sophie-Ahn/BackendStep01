package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.bind.DataBinding;
import _64_Project_Add.dao.MemberDao;
import _64_Project_Add.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/authpAdd/login.do")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", _64_Project_Add.vo.Member.class
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
                return "redirect:../memberpAdd/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
