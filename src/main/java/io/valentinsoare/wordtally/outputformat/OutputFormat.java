package io.valentinsoare.wordtally.outputformat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

/***
 * This OutputFormat class is tagged as a spring boot component to be scanned and injected where is needed automatically when the application is started.
 * As you can see, it is a singleton, and we're going to create only one instance from this class, and we will use it wherever we need to print the error messages in JSON format.
 * In other words with a Jackson library, we serialize the ErrorMessage objects into a String with JSON format, key:value pair.
 */

@Component
public class OutputFormat {
    private ObjectMapper jsonStyle;

    public OutputFormat() {}

    public ObjectMapper withJSONStyle() {
        if (jsonStyle == null) {
            jsonStyle = new ObjectMapper();
            jsonStyle.registerModule(new JavaTimeModule());
        }

        return jsonStyle;
    }
}
