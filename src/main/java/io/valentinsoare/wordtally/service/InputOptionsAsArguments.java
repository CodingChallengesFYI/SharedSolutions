package io.valentinsoare.wordtally.service;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface InputOptionsAsArguments {
    Map<String, List<String>> extractTypeOfTasksAndLocationsFromInput(String[] arguments) throws ParseException;
    void runTasksFromInput(String[] arguments, InputStream inputStream);
    void printHelp(Options options);
    List<String> checkFilesAvailability(List<String> locations);
    List<Long> executeTasks(List<String> options, Path inputFile) throws InterruptedException;

}
