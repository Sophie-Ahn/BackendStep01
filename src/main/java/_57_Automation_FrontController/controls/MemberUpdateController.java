package _57_Automation_FrontController.controls;

import _57_Automation_FrontController.bind.DataBinding;
import _57_Automation_FrontController.dao.MemberDao;
import _57_Automation_FrontController.vo.Member;

import java.util.Map;

@SuppressWarnings("serial")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", _57_Automation_FrontController.vo.Member.class,
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
//        if (model.get("member") == null) {
        Member member = (Member)model.get("member");

        if(member.getEmail() == null){
            Integer no = (Integer)model.get("no");
            Member detailInfo = memberDao.selectOne(no);
            model.put("member", detailInfo);

            return "/member/MemberUpdateForm.jsp";

        } else {
//            Member member = (Member)model.get("member");

            memberDao.update(member);

            return "redirect:list.do";
        }
    }


}
