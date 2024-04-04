package _56_DataBinding.controls;

import _56_DataBinding.bind.DataBinding;
import _56_DataBinding.dao.MemberDao;
import _56_DataBinding.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    /*
    * LoginController가 execute()를 실행하기 위해서는
    * loginInfo라는 이름의 Member클래스 객체가 필요하다.
    * 이 객체를 자동으로 생성해서 담아주기를 바란다.
    */
    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", _56_DataBinding.vo.Member.class
        };
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
                return "redirect:../memberBinding/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
