package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.bind.DataBinding;
import _67_Header_Member_Project_Link.dao.MemberDao;
import _67_Header_Member_Project_Link.vo.Member;

import java.util.Map;

@Component("/memberpHeader/add.do")
public class MemberAddController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");

        if(member.getEmail() == null){
            System.out.println("MemberAddController::execute() - get 요청");
            return "/member/MemberForm.jsp";

        } else {
            System.out.println("MemberAddController::execute() - post 요청");
            memberDao.insert(member);

            return "redirect:list.do";
        }
    }
}
