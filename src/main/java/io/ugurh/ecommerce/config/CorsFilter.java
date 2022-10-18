package io.ugurh.ecommerce.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author harun ugur
 * @project sample
 * @created 14.10.2022 - 00:24
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void destroy() {
        // nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-BBBB-TOKEN, X-Forwarded-For, Proxy-Client-IP, WL-Proxy-Client-IP, HTTP_X_FORWARDED_FOR, HTTP_X_FORWARDED, HTTP_X_CLUSTER_CLIENT_IP, HTTP_CLIENT_IP, HTTP_FORWARDED_FOR, HTTP_FORWARDED, HTTP_VIA, REMOTE_ADDR");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
        }
    }

}