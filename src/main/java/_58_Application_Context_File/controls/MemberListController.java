package _58_Application_Context_File.controls;

import _58_Application_Context_File.dao.MemberDao;

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
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
