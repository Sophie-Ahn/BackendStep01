package _55_MemberDao_To_Interface.controls;

import _55_MemberDao_To_Interface.dao.MemberDao;
import _55_MemberDao_To_Interface.vo.Member;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
public class MemberAddController implements Controller {

    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
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
