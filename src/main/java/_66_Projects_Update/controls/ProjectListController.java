package _66_Projects_Update.controls;

import _66_Projects_Update.annotation.Component;
import _66_Projects_Update.dao.ProjectDao;
import _66_Projects_Update.vo.Project;

import java.util.List;
import java.util.Map;

@Component("/projectpUpdate/list.do")
public class ProjectListController implements Controller {

    ProjectDao projectDao;

    public ProjectListController setMemberDao(ProjectDao projectDao){
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        List<Project> projects = projectDao.selectList();

        System.out.println(projects);

        model.put("projects", projects);

        return "/project/ProjectList.jsp";
    }
}
