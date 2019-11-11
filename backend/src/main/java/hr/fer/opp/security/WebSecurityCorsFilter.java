package hr.fer.opp.security;

import hr.fer.opp.config.SecurityConstants;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebSecurityCorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;

        res.setHeader("Access-Control-Allow-Origin", SecurityConstants.ALLOW_ORIGIN);
        res.setHeader("Access-Control-Allow-Methods", SecurityConstants.ALLOW_METHODS);
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {

    }
}