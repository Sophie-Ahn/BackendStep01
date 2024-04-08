package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.bind.DataBinding;
import _63_Projects_List.dao.MemberDao;
import _63_Projects_List.vo.Member;

import java.util.Map;

@Component("/memberPl/add.do")
public class MemberAddController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", _63_Projects_List.vo.Member.class
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
