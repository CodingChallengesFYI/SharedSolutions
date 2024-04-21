package io.valentinsoare.wordtally.setupasync;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.valentinsoare.wordtally.exception.ErrorMessage;
import io.valentinsoare.wordtally.exception.Severity;
import io.valentinsoare.wordtally.outputformat.OutputFormat;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class AsynchronousRejectionHandler implements RejectedExecutionHandler {

    private final OutputFormat outputFormat;

    public AsynchronousRejectionHandler(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ErrorMessage msg = ErrorMessage.builder()
                .severity(Severity.INFO)
                .clazzName(Executor.class.toString())
                .threadName(Thread.currentThread().getName())
                .methodName("Execute")
                .dateTime(LocalDateTime.now().toString())
                .message("Current task is rejected by the threadPool")
                .build();

        try {
            System.out.printf("%s %n", outputFormat.withJSONStyle().writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            System.out.printf("%s %n", e.getMessage());
        }
    }
}
