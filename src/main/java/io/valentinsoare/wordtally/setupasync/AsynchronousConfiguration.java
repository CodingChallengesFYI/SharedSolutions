package io.valentinsoare.wordtally.setupasync;

import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/***
 * With this we defined the type of executor, and as you can see we have a singleton design pattern here, so we will create
 * an executor from the DynamicThreadPoolManager and then whenever we need we will use it.
 */

@EnableAsync
@Configuration
public class AsynchronousConfiguration implements AsyncConfigurer {
    private DynamicThreadPoolManager defaultDynamicThreadPoolManager;

    public AsynchronousConfiguration() {}

    @Override
    public Executor getAsyncExecutor() {
        if (defaultDynamicThreadPoolManager == null) {
            this.defaultDynamicThreadPoolManager = DynamicThreadPoolManager.generateNewDynamicThreadPoolManager();
        }

        return defaultDynamicThreadPoolManager;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsynchronousExceptionHandler(new OutputFormat());
    }
}
