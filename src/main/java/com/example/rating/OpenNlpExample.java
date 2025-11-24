package com.example.rating;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class OpenNlpExample {
    public static void main(String[] args) {
        try (InputStream modelIn = new FileInputStream(new File("/Users/in45828930/Downloads/rating/src/main/java/com/example/rating/da-sent.bin"))) {
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

            String javaComment = "   CompletableFuture<ResponseEntity<String>> responseFuture = CompletableFuture.supplyAsync(() -> {\n" +
                    "            try {\n" +
                    "                return restTemplate.getForEntity(FEED_URL, String.class);\n" +
                    "            } catch (Exception e) {\n" +
                    "                throw new RuntimeException(\"Failed to fetch data\", e);\n" +
                    "            }\n" +
                    "        });";
            String[] sentences = sentenceDetector.sentDetect(javaComment);

            for (String sentence : sentences) {
                System.out.println(sentence);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
