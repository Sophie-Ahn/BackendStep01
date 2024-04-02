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
@WebServlet("/memberQController/delete")
public class MemberDeleteController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao)model.get("memberDao");

        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
