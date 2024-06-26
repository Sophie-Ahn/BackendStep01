package _14_MemberUpdate_ServletInParam.spms.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*get요청은 소스에 하지 않고 tomcat xml에 저장
* 파라미터에 꺄내기 전에 해야함
* 이 설정을 안해주면 한글이 깨져서 저장됨
* 이 설정을 추가하로 해줄 것*/
@SuppressWarnings("serial")
@WebServlet("/memberUpdate/list")
public class MemberListServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse reps) throws ServletException, IOException {
        Connection conn = null; // DB 서버와의 연결 객체
        Statement stmt = null; // SQL문
        ResultSet rs = null; // Select문의 결과 정보

        // get은 화면에 뿌려지는것
        // post는 디비에 전달해주는것
        try{
            // 메모리에 클래스 로딩
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:4306/studydb", // jdbc url
                    "study", // id
                    "study"); // password
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT mno, mname,email, cre_date" +
                    " FROM members" +
                    " ORDER BY mno ASC");
            reps.setContentType("text/html;charset=UTF-8");
            PrintWriter out = reps.getWriter();
            out.println("<html><head><title>회원목록</title></head>");
            out.println("<body><h1>회원목록</h1></body>");
            /*신규회원 추가
            * href = '/add' => 절대경로
            * localhost:9999/<contextRoot>/add
            *
            * href = 'add' => 상대경로
            * loscalhost:9999/<contextRoot>/member/add
            * */
            out.println("<p><a href='add'>신규회원</a></p>");
            while (rs.next()){
                out.println(
                        rs.getInt("mno") + ", " +
                                "<a href='update?no=" + rs.getInt("mno") +"'>"+
                        rs.getString("mname") + "</a>, " +
                        rs.getString("email") + ", " +
                        rs.getDate("cre_date") + "<br>"
                );
            }
            out.println("</body></html>");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            // 생성한 역순으로 닫아준다.
            try {if(rs !=null) rs.close();}catch (Exception e){}
            try {if(stmt !=null) stmt.close();}catch (Exception e){}
            try {if(conn !=null) conn.close();}catch (Exception e){}
        }

    }
}
