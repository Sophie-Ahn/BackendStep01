package _66_Projects_Update.servlet;

import _66_Projects_Update.bind.DataBinding;
import _66_Projects_Update.bind.ServletRequestDataBinder;
import _66_Projects_Update.context.ApplicationContext;
import _66_Projects_Update.controls.Controller;
import _66_Projects_Update.listeners.ContextLoaderListener;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private void prepareRequestData(HttpServletRequest req, // 브라우저가 보내온 Parameter
                                    Map<String, Object>model, // 자동 생성된 객체를 저장할 model 객체
                                    DataBinding dataBinding // Controller에서 자동 생성 정보 추출를 위한 인터페이스
    ) throws Exception{
        Object[] dataBinders = dataBinding.getDataBinders();
        String dataName = null; // 생성할 객체 이름
        Class<?> dataType = null; // 데이터 클래스 타입
        Object dataObj = null; // 생성된 객체

        for(int i = 0; i<dataBinders.length; i+=2){
            dataName = (String)dataBinders[i];
            dataType = (Class<?>)dataBinders[i+1];

            dataObj = ServletRequestDataBinder.bind(req, dataType, dataName);

            model.put(dataName, dataObj);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            String servletPath = req.getServletPath();
            System.out.println("DispatchServlet::service() - servletPath=" + servletPath);

            Map<String, Object> model = new ConcurrentHashMap<>();
            model.put("session", req.getSession());

//            Controller pageController = (Controller)this.getServletContext().getAttribute(servletPath);
            ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
            Controller pageController = (Controller)ctx.getBean(servletPath);
            if(pageController == null){
                throw new Exception("요청한 Controller 객체를 찾을 수 없어서, 서비스 제공 불가");
            }

            if(pageController instanceof DataBinding){
                prepareRequestData(req, model, (DataBinding)pageController);
            }

            System.out.println("DispatchServlet::service() - pageController=" + pageController.getClass().getName());
            String viewUrl = pageController.execute(model);

            for(String key : model.keySet()){
                req.setAttribute(key, model.get(key));
            }

            System.out.println("DispatchServlet::service() - viewUrl = " + viewUrl);

            if(viewUrl.startsWith("redirect:")){
                resp.sendRedirect(viewUrl.substring("redirect:".length()));
                return;
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
                rd.include(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }
}
