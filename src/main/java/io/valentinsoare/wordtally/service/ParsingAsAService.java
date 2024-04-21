package io.valentinsoare.wordtally.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public interface ParsingAsAService {
   CompletableFuture<Long> countTheNumberOfLines(Path inputFile);
   CompletableFuture<Long> countTheNumberOfChars(Path inputFile);
   boolean checkTheReaderIsReady(InputStream inputStream) throws JsonProcessingException;
   CompletableFuture<Long> countTheNumberOfWords(Path inputFile);
   CompletableFuture<Long> countTheNumberOfBytes(Path inputFile);
}
