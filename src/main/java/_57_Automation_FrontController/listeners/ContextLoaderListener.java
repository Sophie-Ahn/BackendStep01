package _57_Automation_FrontController.listeners;

import _57_Automation_FrontController.controls.*;
import _57_Automation_FrontController.dao.MysqlMemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");
        try {
            ServletContext sc = sce.getServletContext();

            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup(
                    "java:comp/env/jdbc/studydb");

            MysqlMemberDao memberDao = new MysqlMemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("/authAuto/login.do", new LoginController().setMemberDao(memberDao));
            sc.setAttribute("/authAuto/logout.do", new LogoutController());
            sc.setAttribute("/memberAuto/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/memberAuto/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/memberAuto/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/memberAuto/delete.do", new MemberDeleteController().setMemberDao(memberDao));
//            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");

    }
}
