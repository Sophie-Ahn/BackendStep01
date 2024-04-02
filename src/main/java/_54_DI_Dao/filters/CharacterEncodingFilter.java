package _54_DI_Dao.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name="encoding", value="UTF-8")
        })
public class CharacterEncodingFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        System.out.println("CharaterEncodingFilter::init() 호출");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain nextFilter) throws IOException, ServletException {
        // web.xml에 필터를 등록하고, 초기화 매개변수를 얻어옴
        req.setCharacterEncoding(config.getInitParameter("encoding"));
        System.out.println("CharaterEncodingFilter::doFilter() 호출");

        // 다음 필터가 있으면 다음 필터에 전달되고, 없으면 해당 주소에 일치하는 서블릿 객체에 전달된다.
        nextFilter.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("CharaterEncodingFilter::destroy() 호출");
    }
}
