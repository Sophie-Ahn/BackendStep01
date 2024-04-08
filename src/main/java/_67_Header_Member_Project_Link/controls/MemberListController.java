package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.dao.MemberDao;

import java.util.Map;

@Component("/memberpHeader/list.do")
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
