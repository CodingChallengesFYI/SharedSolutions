package io.valentinsoare.wordtally.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.valentinsoare.wordtally.exception.ErrorMessage;
import io.valentinsoare.wordtally.exception.Severity;
import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 *  Here, as you can see by the name, we have the design on how to process the input coming from inputStream (System.in)
 * Here we tackle the input data in a simple way.
 *
 * This is the first version, but in the next one I will use async and parallel processing here and define more methods, one per type of task. In this case, each time we call a method
 * we need to mark the BufferReader object, and then if another method is called, we do a reader. Reset to reset the inputStream to
 * beginning and then mark it again and start the app logic.
 *
 * Also, we are going to need more BufferReader objects, one BufferReader per type of task,
 * and when we create these bufferReaders, we need to use reset in other to go to the beginning of the initial inputStream
 * */

@Service
public class ProcessingTheInputFromFD implements ProcessingAsAService {

    private final OutputFormat outputFormat;

    @Autowired
    public ProcessingTheInputFromFD(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    public List<Long> countingAndPrinting(InputStream inputStream, boolean toCountLines, boolean toCountWords, boolean toCountChars, boolean toCountBytes) {
        long numberOfChars = 0, numberOfLines = 0, numberOfWords = 0, numberOfBytes = 0;
        inputStream.mark(Integer.MAX_VALUE);

        if (toCountBytes) {
            int readBytes;
            byte[] buffer = new byte[1024];

            try {
                while ((readBytes = inputStream.read(buffer)) != -1) {
                    numberOfBytes += readBytes;
                }
            } catch (IOException r) {
                ErrorMessage msg = ErrorMessage.builder()
                        .methodName("countingAndPrinting")
                        .threadName(Thread.currentThread().getName())
                        .clazzName(this.getClass().getName())
                        .dateTime(Instant.now().toString())
                        .severity(Severity.ERROR)
                        .message(r.getMessage())
                        .build();

                try {
                    System.out.printf("%s %n", outputFormat.withJSONStyle().writeValueAsString(msg));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try {
            int byteRead;
            boolean isWord = false;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            inputStream.reset();

            while ((byteRead = reader.read()) != -1) {

                if ((toCountLines) && (byteRead == '\n')) {
                    numberOfLines += 1;
                }

                if (toCountWords) {
                    if (Character.isWhitespace(byteRead)) {
                        if (isWord) {
                            isWord = false;
                        }
                    } else {
                        if (!isWord) {
                            isWord = true;
                            numberOfWords += 1;
                        }
                    }
                }

                if (toCountChars) {
                    numberOfChars += 1;
                }
            }

        } catch (IOException er) {
            ErrorMessage msg = ErrorMessage.builder()
                    .threadName(Thread.currentThread().getName())
                    .methodName("countingAndPrinting")
                    .clazzName(this.getClass().getName())
                    .message(er.getMessage())
                    .dateTime(Instant.now().toString())
                    .severity(Severity.ERROR)
                    .build();

            try {
                System.out.printf("%s %n", outputFormat.withJSONStyle().writeValueAsString(msg));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        List<Long> tmp = new ArrayList<>(Arrays.asList(
                numberOfLines, numberOfWords, numberOfChars, numberOfBytes
        ));

        List<Long> end = new ArrayList<>();

        tmp.forEach(e -> {
            if (e != 0) end.add(e);
        });

        return end;
    }

    @Override
    public List<Long> execTheTasksWithCountingInParallelWithParallelStreams(List<String> options, InputStream inputStream) {
        boolean toCountLines = false, toCountWords = false, toCountChars = false, toCountBytes = false;

        for (String s : options) {
            switch (s) {
                case "lines" -> toCountLines = true;
                case "words" -> toCountWords = true;
                case "chars" -> toCountChars = true;
                case "bytes" -> toCountBytes = true;
            }
        }

        return countingAndPrinting(inputStream, toCountLines, toCountWords, toCountChars, toCountBytes);
    }
}
