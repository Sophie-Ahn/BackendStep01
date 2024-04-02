package _52_Question.controls;

import _52_Question.dao.MemberDao;
import _52_Question.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/memberQController/update")
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
