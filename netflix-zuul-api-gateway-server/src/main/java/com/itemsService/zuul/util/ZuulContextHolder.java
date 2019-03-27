package com.itemsService.zuul.util;

import org.springframework.util.Assert;

public class ZuulContextHolder {

    private static final ThreadLocal<ZuulContext> userContext = new ThreadLocal<ZuulContext>();

    public static final ZuulContext getContext() {
        ZuulContext context = userContext.get();

        if(context==null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final void setContext(ZuulContext context) {
        Assert.notNull(context, "Only non-null UserContext instance are permitted");
        userContext.set(context);
    }

    public static final ZuulContext createEmptyContext() {
        return new ZuulContext();
    }

}
