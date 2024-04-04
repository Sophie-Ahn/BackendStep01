package _59_Automation_Annotation.controls;

import _59_Automation_Annotation.annotation.Component;
import _59_Automation_Annotation.bind.DataBinding;
import _59_Automation_Annotation.dao.MemberDao;

import java.util.Map;

@Component("/memberAppAno/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Integer no = (Integer)model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
