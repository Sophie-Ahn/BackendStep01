package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.bind.DataBinding;
import _64_Project_Add.dao.MemberDao;
import _64_Project_Add.vo.Member;

import java.util.Map;

@Component("/memberpAdd/add.do")
public class MemberAddController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", _64_Project_Add.vo.Member.class
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
