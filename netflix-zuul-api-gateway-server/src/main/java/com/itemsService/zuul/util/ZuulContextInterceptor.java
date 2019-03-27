package com.itemsService.zuul.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ZuulContextInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ZuulContextInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(ZuulContext.CORRELATION_ID, ZuulContextHolder.getContext().getCorrelationId());
        headers.add(ZuulContext.AUTH_TOKEN, ZuulContextHolder.getContext().getAuthToken());

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
