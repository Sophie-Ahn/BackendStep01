package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.bind.DataBinding;
import _67_Header_Member_Project_Link.dao.ProjectDao;

import java.util.Map;

@Component("/projectpHeader/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {

    ProjectDao projectDao;
    public ProjectDeleteController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
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

        projectDao.delete(no);

        return "redirect:list.do";
    }
}
