package _65_Projects_Delete.controls;

import _65_Projects_Delete.annotation.Component;
import _65_Projects_Delete.bind.DataBinding;
import _65_Projects_Delete.dao.ProjectDao;
import _65_Projects_Delete.vo.Project;

import java.util.Map;

@Component("/projectpDelete/add.do")
public class ProjectAddController implements Controller, DataBinding {
    ProjectDao projectDao;
    public ProjectAddController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "project", _65_Projects_Delete.vo.Project.class
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
