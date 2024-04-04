package _56_DataBinding.controls;

import _56_DataBinding.dao.MemberDao;

import java.util.Map;

@SuppressWarnings("serial")
public class MemberListController implements Controller {

    MemberDao memberDao;

    public MemberListController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        System.out.println("MemberListController::execute() 호출");

//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
