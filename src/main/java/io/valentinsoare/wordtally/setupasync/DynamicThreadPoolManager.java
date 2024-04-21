package io.valentinsoare.wordtally.setupasync;

import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.*;


/***
 * Here we defined the ThreadPoolExecutor with values that are given when creating the object from AsynchronousConfiguration class.
 */

public class DynamicThreadPoolManager implements TaskExecutor {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveTime;
    private int arrayQueueSize;

    private String nameOfTheWorkingThread;
    private ThreadPoolExecutor threadPoolExecutor;

    private DynamicThreadPoolManager() {
        this.corePoolSize = 6;
        this.maxPoolSize = 10;
        this.keepAliveTime = 35;
        this.arrayQueueSize = 500;
        this.nameOfTheWorkingThread = "working-thread";

        ThreadFactory threadFactoryExecTasks = (r -> {
            Thread t = new Thread(r);
            t.setName(String.format("%s-%s", nameOfTheWorkingThread, t.getId()));
            return t;
        });

        threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(arrayQueueSize),
                threadFactoryExecTasks,
                new AsynchronousRejectionHandler(new OutputFormat())
        );
    }

    public static DynamicThreadPoolManager generateNewDynamicThreadPoolManager() {
        return new DynamicThreadPoolManager();
    }

    @Override
    public void execute(Runnable task) {
        threadPoolExecutor.execute(task);
    }
}
