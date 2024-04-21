package io.valentinsoare.wordtally;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Here we bootstrap and starting the application and the ApplicationRunner class will run to process
 * the command line arguments of this app.
 */

@SpringBootApplication
public class WordTallyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordTallyApplication.class, args);
    }
}
