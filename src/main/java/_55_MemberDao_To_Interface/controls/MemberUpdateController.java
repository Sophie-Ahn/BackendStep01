package _55_MemberDao_To_Interface.controls;

import _55_MemberDao_To_Interface.dao.MemberDao;
import _55_MemberDao_To_Interface.vo.Member;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
public class MemberUpdateController implements Controller {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        MemberDao memberDao = (MemberDao)model.get("memberDao");

        if (model.get("member") == null) {
            Integer no = (Integer)model.get("no");
            Member member = memberDao.selectOne(no);
            model.put("member", member);

            return "/member/MemberUpdateForm.jsp";
        } else {
            Member member = (Member)model.get("member");
            memberDao.update(member);

            return "redirect:list.do";
        }
    }
}
