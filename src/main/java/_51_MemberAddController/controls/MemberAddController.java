package _51_MemberAddController.controls;

import _51_MemberAddController.dao.MemberDao;
import _51_MemberAddController.vo.Member;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/memberAddController/add")
@SuppressWarnings("serial")
public class MemberAddController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if(model.get("member") == null){ // get요청
            System.out.println("MemberAddController::execute() - get 요청");
            return "/member/MemberForm.jsp";

        } else { // post요청
            System.out.println("MemberAddController::execute() - post 요청");

            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member member = (Member)model.get("member");
            memberDao.insert(member);

            return "redirect:list.do";
        }
    }
}
