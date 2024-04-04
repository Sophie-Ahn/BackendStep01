package _56_DataBinding.controls;

import _56_DataBinding.bind.DataBinding;
import _56_DataBinding.dao.MemberDao;
import _56_DataBinding.vo.Member;

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
                "member", _56_DataBinding.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if(model.get("member") == null){ // get요청
            System.out.println("MemberAddController::execute() - get 요청");
            return "/member/MemberForm.jsp";

        } else { // post요청
            System.out.println("MemberAddController::execute() - post 요청");

//            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member member = (Member)model.get("member");
            memberDao.insert(member);

            return "redirect:list.do";
        }
    }


}
