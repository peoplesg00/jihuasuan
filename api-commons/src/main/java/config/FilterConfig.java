package config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import redis.RedisUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: LI
 * @Date: 2020/6/17 13:47
 * @Description:
 */
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "FilterConfig")
@Slf4j
public class FilterConfig implements Filter {

    private RedisUtils redisUtils;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        //  这里最好明确的写允许的域名
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg");
        chain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }
}
