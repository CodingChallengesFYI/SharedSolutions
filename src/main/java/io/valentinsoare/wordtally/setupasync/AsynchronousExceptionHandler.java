package io.valentinsoare.wordtally.setupasync;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.valentinsoare.wordtally.exception.ErrorMessage;
import io.valentinsoare.wordtally.exception.Severity;
import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AsynchronousExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final OutputFormat outputFormat;

    public AsynchronousExceptionHandler(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        ErrorMessage msg = ErrorMessage.builder()
                .message(ex.getMessage())
                .methodName(method.getName())
                .threadName(Thread.currentThread().getName())
                .dateTime(LocalDateTime.now().toString())
                .severity(Severity.ERROR)
                .clazzName(method.getDeclaringClass().getName())
                .build();

        try {
            System.out.printf("%s %n", outputFormat.withJSONStyle().writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            System.out.printf("%s %n", e.getMessage());
        }
    }
}
