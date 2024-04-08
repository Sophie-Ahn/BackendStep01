package _67_Header_Member_Project_Link.controls;

import _67_Header_Member_Project_Link.annotation.Component;
import _67_Header_Member_Project_Link.bind.DataBinding;
import _67_Header_Member_Project_Link.dao.ProjectDao;
import _67_Header_Member_Project_Link.vo.Project;

import java.util.Map;

@Component("/projectpHeader/add.do")
public class ProjectAddController implements Controller, DataBinding {
    ProjectDao projectDao;
    public ProjectAddController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "project", Project.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");
        if(project.getTitle() == null){
            return "/project/ProjectForm.jsp";
        } else {
            projectDao.insert(project);
            return "redirect:list.do";
        }
    }
}
