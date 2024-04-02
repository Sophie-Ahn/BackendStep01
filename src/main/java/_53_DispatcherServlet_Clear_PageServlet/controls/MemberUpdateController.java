package _53_DispatcherServlet_Clear_PageServlet.controls;

import _53_DispatcherServlet_Clear_PageServlet.dao.MemberDao;
import _53_DispatcherServlet_Clear_PageServlet.vo.Member;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/memberClearController/update")
public class MemberUpdateController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao)model.get("memberDao");

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
