package _57_Automation_FrontController.controls;

import _57_Automation_FrontController.bind.DataBinding;
import _57_Automation_FrontController.dao.MemberDao;
import _57_Automation_FrontController.vo.Member;

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
                "loginInfo", _57_Automation_FrontController.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        if(model.get("loginInfo") == null) { // 입력폼을 요청할 때

        // 현재 execute에서 사용하는 객체를 자동생성하는 방식이 get/post 가리지 않고 무조건 객체를 생성하는 방식이라
        // 기존의 loginInfo가 존재하는지로 판단할 수 없어서 loginInfo에 email 정보가 존재하는지로 조건을 변경한다.
        Member loginInfo = (Member)model.get("loginInfo");
        if(loginInfo.getEmail() == null){
            System.out.println("LoginController::execute() - get 요청");
            return "/auth/LoginForm.jsp";
        } else { // 회원등록을 요청할 때
//            MemberDao memberDao = (MemberDao)model.get("memberDao");
//            Member loginInfo = (Member)model.get("loginInfo");

            Member member = memberDao.exist(
                    loginInfo.getEmail(),
                    loginInfo.getPassword()
            );

            if(member != null){
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../memberAuto/list.do";
            } else {
                return "/auth/LoginFail.jsp";
            }
        }
    }

}
