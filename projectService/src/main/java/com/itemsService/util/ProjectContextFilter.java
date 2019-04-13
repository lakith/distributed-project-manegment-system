package com.itemsService.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ProjectContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ProjectContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ProjectContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(ProjectContext.CORRELATION_ID));
        ProjectContextHolder.getContext().setUserId(httpServletRequest.getHeader(ProjectContext.USER_ID));
        ProjectContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(ProjectContext.AUTH_TOKEN));

        logger.info("ZuulContextFilter Correlation id: {}", ProjectContextHolder.getContext().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
