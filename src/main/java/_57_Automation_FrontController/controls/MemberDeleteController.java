package _57_Automation_FrontController.controls;

import _57_Automation_FrontController.bind.DataBinding;
import _57_Automation_FrontController.dao.MemberDao;

import java.util.Map;

@SuppressWarnings("serial")
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
