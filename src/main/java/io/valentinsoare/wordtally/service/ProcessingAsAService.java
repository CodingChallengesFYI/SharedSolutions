package io.valentinsoare.wordtally.service;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProcessingAsAService {
    List<Long> execTheTasksWithCountingInParallelWithParallelStreams(List<String> options, InputStream inputStream);
    List<Long> countingAndPrinting(InputStream inputStream, boolean toCountLines, boolean toCountWords, boolean toCountChars, boolean toCountBytes) throws IOException;
}