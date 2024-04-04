package _56_DataBinding.listeners;

import _56_DataBinding.controls.*;
import _56_DataBinding.dao.MysqlMemberDao;

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

            sc.setAttribute("/authBinding/login.do", new LoginController().setMemberDao(memberDao));
            sc.setAttribute("/authBinding/logout.do", new LogoutController());
            sc.setAttribute("/memberBinding/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/memberBinding/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/memberBinding/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/memberBinding/delete.do", new MemberDeleteController().setMemberDao(memberDao));
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
