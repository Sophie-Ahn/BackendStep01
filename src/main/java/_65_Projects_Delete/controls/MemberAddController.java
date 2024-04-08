package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.bind.DataBinding;
import _65_Projects_Delete.dao.MemberDao;
import _65_Projects_Delete.vo.Member;

import java.util.Map;

@Component("/memberpDelete/add.do")
public class MemberAddController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", _65_Projects_Delete.vo.Member.class
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
