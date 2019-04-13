package com.itemsService.hystrix;

import com.itemsService.util.ProjectContext;
import com.itemsService.util.ProjectContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private ProjectContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate, ProjectContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    public V call() throws Exception {
        ProjectContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static<V> Callable<V> create (Callable<V> delegate, ProjectContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}