package _65_Projects_Delete.listeners;

import _65_Projects_Delete.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    /*
    * ContextLoaderListener는 WebApp이 시작될 때 이벤트 통보를 받는 역할이므로
    * 이후에는 사용하지 않음
    * 그러므로 객체를 별도로 접근하지 않기 때문에 객체를 통해 메서드를 호출하지 않고
    * 클래스를 통해서 어디에서나 호출할 수 있도록 static을 사용한다.
    * 아래처럼 접근해서 사용하려는 의도
    * ApplicationContext appContext = ContextLoaderListener.getApplicationContext();
    * */
    static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");

        try {
            ServletContext sc = sce.getServletContext();

            String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
            applicationContext = new ApplicationContext(propertiesPath);

            /*InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup(
                    "java:comp/env/jdbc/studydb");

            MysqlMemberDao memberDao = new MysqlMemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("/authApp/login.do", new LoginController().setMemberDao(memberDao));
            sc.setAttribute("/authApp/logout.do", new LogoutController());
            sc.setAttribute("/memberApp/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/memberApp/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/memberApp/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/memberApp/delete.do", new MemberDeleteController().setMemberDao(memberDao));*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");

    }
}
