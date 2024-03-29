package _25_MemberListServlet_Include_jsp.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/membeInclude/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>회원 등록</title></head>");
        out.println("<body><h1>회원 등록</h1>");
        out.println("<html><head><title>회원 등록</title></head>");
        out.println("<form action='add' method='post'>");
        out.println("이름: <input type='text' name='name'><br>");
        out.println("이메일: <input type='text' name='email'><br>");
        out.println("암호: <input type='text' name='password'><br>");
        out.println("<input type='submit' value='추가'>");
        out.println("<input type='reset' value='취소'>");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // CharacterEncodingFilter로 전처리 했으므로 이제 안해도 됨
//        req.setCharacterEncoding("UTF-8");
        Connection conn = null;
        /*
        * Statement
        * - 질의할 때마다 SQL을 컴파일한다.
        * - 입력 매개변수가 여러 개 필요할 때 문자열 결합연산자인 +를 이용해서 해야한다.
        * - 전송 직접에 sql문을 입력받고, 컴파일 후 서버로 전송
        *
        * PreparedStatement
        * - sql문을 미리 입력하여 컴파일한 상태에서 객체를 받는다.
        * - 만약에 sql문 구조가 변경되지 않고, 파라미터값만 바뀌는 경우 Statement보다 훨씬 빠르다.
        * - 입력 매개변수가 여러개 필요할 때 ?로 sql의 파라미터를 표시하고, 나중에 전달하므로 편하다.
        * - Statement < PreparedStatement를 사용한다.
        * */
        PreparedStatement pstmt = null;

        try{
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")
            );
            pstmt = conn.prepareStatement(
                    "INSERT INTO members(email, pwd, mname, cre_date,mod_date)" +" VALUES (?, ?, ?, NOW(), NOW())"
            );
            pstmt.setString(1, req.getParameter("email"));
            pstmt.setString(2, req.getParameter("password"));
            pstmt.setString(3, req.getParameter("name"));
            pstmt.executeUpdate();

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>회원등록결과</title></head>");
            out.println("<body>");
            out.println("<p>회원등록 성공입니다</p>");
            out.println("</body></html>");

            resp.sendRedirect("list");
            // 1초후에 화면이 바뀌면서 강제로 되는것
//            resp.addHeader("Refresh", "1;url=list");
        }catch (Exception e){
            throw new ServletException(e);
        }finally {

            try {if(pstmt !=null) pstmt.close();}catch (Exception e){}
            try {if(conn !=null) conn.close();}catch (Exception e){}
        }
    }
}
