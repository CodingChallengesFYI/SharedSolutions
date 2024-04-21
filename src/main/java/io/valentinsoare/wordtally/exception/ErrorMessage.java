package io.valentinsoare.wordtally.exception;

import lombok.Builder;
import lombok.Data;

/***
 * This ErrorMessage page represents a template for error messages that appear in the application.
 * When there is an error/exception, an object is created from this specific class and then serialized
 * and printed in JSON format as a key-value pair in string format in the terminal.
 */

@Data
@Builder
public class ErrorMessage {
    private Severity severity;
    private String clazzName;
    private String methodName;
    private String threadName;
    private String message;
    private String dateTime;

    public ErrorMessage() {}

    public ErrorMessage(Severity severity, String clazzName, String methodName,
                        String threadName, String message, String dateTime) {
        this.severity = severity;
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.threadName = threadName;
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("Severity: %s, ClazzName: %s, MethodName: %s, ThreadName: %s, Message: %s, DateTime: %s",
                severity, clazzName, methodName, threadName, message, dateTime);
    }
}
