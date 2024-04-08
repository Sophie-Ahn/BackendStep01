package _63_Projects_List.controls;

import _63_Projects_List.annotation.Component;
import _63_Projects_List.dao.ProjectDao;
import _63_Projects_List.vo.Project;

import java.util.List;
import java.util.Map;

@Component("/projectPl/list.do")
public class ProjectListController implements Controller{

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
