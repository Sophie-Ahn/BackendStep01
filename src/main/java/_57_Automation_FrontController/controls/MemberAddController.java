package _57_Automation_FrontController.controls;

import _57_Automation_FrontController.bind.DataBinding;
import _57_Automation_FrontController.dao.MemberDao;
import _57_Automation_FrontController.vo.Member;

import java.util.Map;

@SuppressWarnings("serial")
public class MemberAddController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", _57_Automation_FrontController.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        if(model.get("member") == null){ // get요청

        Member member = (Member)model.get("member");

        if(member.getEmail() == null){
            System.out.println("MemberAddController::execute() - get 요청");
            return "/member/MemberForm.jsp";

        } else { // post요청
            System.out.println("MemberAddController::execute() - post 요청");

//            Member member = (Member)model.get("member");
            memberDao.insert(member);

            return "redirect:list.do";
        }
    }


}
