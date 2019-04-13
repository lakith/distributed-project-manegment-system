package com.itemsService.util;

import org.springframework.util.Assert;

public class ProjectContextHolder {

    private static final ThreadLocal<ProjectContext> userContext = new ThreadLocal<ProjectContext>();

    public static final ProjectContext getContext() {
        ProjectContext context = userContext.get();

        if(context==null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final void setContext(ProjectContext context) {
        Assert.notNull(context, "Only non-null UserContext instance are permitted");
        userContext.set(context);
    }

    public static final ProjectContext createEmptyContext() {
        return new ProjectContext();
    }

}
