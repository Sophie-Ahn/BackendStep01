package _43_Tomcat_DataSource.listeners;

import _43_Tomcat_DataSource.dao.MemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/* 웹 어플리케이션이 실행되었을 때 자동으로 호출되는 클래스
* ServletContext 영역이 준비되었습니다...
*
* @WebListener를 안쓰면 web.xml에 따로 작성을 해야함
* */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {
//    Connection conn;
//    DBConnectionPool connPool;
//    BasicDataSource ds;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 호출");
        try {
            ServletContext sc = sce.getServletContext();

            // Tomcat이 실행될 때 생성되는 DataSource를 소스상에서 접근하기 위함
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup(
                    "java:com/env/jdbc/studydb"
            );
//            ds = new BasicDataSource();
//            ds.setDriverClassName(sc.getInitParameter("driver"));
//            ds.setUrl(sc.getInitParameter("url"));
//            ds.setUsername(sc.getInitParameter("username"));
//            ds.setPassword(sc.getInitParameter("password"));

            MemberDao memberDao = new MemberDao();
            memberDao.setDataSource(ds);

//            connPool = new DBConnectionPool(
//                    sc.getInitParameter("driver"),
//                    sc.getInitParameter("url"),
//                    sc.getInitParameter("username"),
//                    sc.getInitParameter("password")
//            );
//            MemberDao memberDao = new MemberDao();
//            memberDao.setDBConnectionPool(connPool);

            // connection객체대신 memberDao객체를 저장
            sc.setAttribute("memberDao", memberDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 호출");
        try{
            /* Tomcat의 context.xml의 설정중에
            * claseMethod="close"를 하면
            * Tomcat이 종료되면 자동으로
            * DataSource의 close()를 호출하도록 설정했으므로
            * 여기서는 close() 하면 안된다.
            *
            * 왜냐하면 다른 Application이 DataSource를 사용할 수 있으므로
            */
//            if(ds != null) {
//                ds.close();
//            }
//            connPool.closeAll();
//            if(conn != null && conn.isClosed() == false){
//                conn.close();
//            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
