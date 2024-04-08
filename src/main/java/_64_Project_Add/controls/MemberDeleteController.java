package _64_Project_Add.controls;

import _64_Project_Add.annotation.Component;
import _64_Project_Add.bind.DataBinding;
import _64_Project_Add.dao.MemberDao;

import java.util.Map;

@Component("/memberpAdd/delete.do")
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
