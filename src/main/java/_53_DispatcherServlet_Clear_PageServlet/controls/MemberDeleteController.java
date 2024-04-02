package _53_DispatcherServlet_Clear_PageServlet.controls;

import _53_DispatcherServlet_Clear_PageServlet.dao.MemberDao;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/memberClearController/delete")
public class MemberDeleteController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao)model.get("memberDao");

        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
