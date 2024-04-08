package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.bind.DataBinding;
import _67_Header_Member_Project_Link.dao.ProjectDao;
import _67_Header_Member_Project_Link.vo.Project;

import java.util.Map;

@Component("/projectpHeader/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectUpdateController setMemberDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "project", Project.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");

        if(project.getTitle()==null) {
            Integer no = (Integer)model.get("no");
            Project detailInfo = projectDao.selectOne(no);
            model.put("project", detailInfo);
            return "/project/ProjectUpdateForm.jsp";

        }else {
            projectDao.update(project);
            return "redirect:list.do";
        }
    }
}
