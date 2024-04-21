package io.valentinsoare.wordtally.runner;

import io.valentinsoare.wordtally.service.InputOptionsAsArguments;
import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;

/***
 * This is in other words the starting point of the app where we catch the inputStream, in other words, the input redirected to our app using a pipe or redirect.
 * This redirected content is stacked in /proc/PID-of-our-app/fd/0 which is input file descriptor for our application.
 * This inputStream in java can be caught with "System.in" and then we can work on it.
 *
 * Also, we have a service automatically injected here by spring-boot called "actOnInputOptionsAsArguments"
 * and we are using it to run the method starting the entire logic of the app.
 *
 * As you can see, this runner is using an interface that helps us to interact and process the command line arguments.
 */

@Getter
@Component
public class ApplicationRunner implements CommandLineRunner {
    private InputStream inputStream;

    private final InputOptionsAsArguments actOnInputOptionsAsArguments;

    public ApplicationRunner(InputOptionsAsArguments actOnInputOptionsAsArguments) {
        this.actOnInputOptionsAsArguments = actOnInputOptionsAsArguments;
        this.inputStream = System.in;
    }

    @Override
    public void run(String... args) {
        actOnInputOptionsAsArguments.runTasksFromInput(args, inputStream);
        System.exit(0);
    }
}
