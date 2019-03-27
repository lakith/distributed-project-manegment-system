package com.itemsService.zuul.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ZuulContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ZuulContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ZuulContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(ZuulContext.CORRELATION_ID));
        ZuulContextHolder.getContext().setUserId(httpServletRequest.getHeader(ZuulContext.USER_ID));
        ZuulContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(ZuulContext.AUTH_TOKEN));

        logger.info("ZuulContextFilter Correlation id: {}", ZuulContextHolder.getContext().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
