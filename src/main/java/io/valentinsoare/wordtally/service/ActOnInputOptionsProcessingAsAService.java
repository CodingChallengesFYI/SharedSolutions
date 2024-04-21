package io.valentinsoare.wordtally.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.valentinsoare.wordtally.exception.ErrorMessage;
import io.valentinsoare.wordtally.exception.Severity;
import io.valentinsoare.wordtally.outputformat.OutputFormat;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/***
 * This class is tagged as a service and injected into the runner and
 * then called runTasksFromInput method from the runner to start the options and arguments processing.
 *
 * In other words, this method runTasksFromInput is the entry point into the app design system.
 */

@Service
public class ActOnInputOptionsProcessingAsAService implements InputOptionsAsArguments {
    private final OutputFormat outputFormat;
    private final ParsingAsAService parsingAsAService;
    private final ProcessingAsAService processingAsAService;
    private Options requiredOptions;

    @Autowired
    private ActOnInputOptionsProcessingAsAService(OutputFormat outputFormat,
                                                 ParsingAsAService parsingAsAService,
                                                 ProcessingAsAService processingAsAService) {
        this.outputFormat = outputFormat;
        this.parsingAsAService = parsingAsAService;
        this.processingAsAService = processingAsAService;

        prepareOptionsAvailable();
    }

    private void prepareOptionsAvailable() {
        this.requiredOptions = new Options();

        Option lines = createOption("l", "lines", "print the newline counts");
        Option words = createOption("w", "words", "print the word counts");
        Option chars = createOption("m", "chars", "print the character counts");
        Option bytes = createOption("c", "bytes", "print the byte counts");
        Option help = createOption("h", "help", "print the help page");

        requiredOptions.addOption(lines)
                .addOption(words)
                .addOption(chars)
                .addOption(bytes)
                .addOption(help);
    }

    private Option createOption(String shortName, String longName,
                                String description) {
        return Option.builder(shortName)
                .longOpt(longName)
                .desc(description)
                .required(false)
                .build();
    }

    @Override
    public void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();

        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            printWriter.printf("%nWordTally v0.0.1%n");

            printWriter.printf("%n%s%n%n",
                    "Application counts the number of lines, words, characters, and bytes\nfrom one or more text files or from keyboard and prints the results to standard output.");
            helpFormatter.printUsage(printWriter, 100, "java -jar wordtally.jar [OPTION]...  [FILE]...");
            helpFormatter.printOptions(printWriter, 100, options, 2, 5);
            printWriter.printf("%n%s%n%n", "WordTally was written by Valentin Soare.\nPlease report any bugs to soarevalentinn@gmail.com.");
        }

        System.exit(0);
    }

    @Override
    public Map<String, List<String>> extractTypeOfTasksAndLocationsFromInput(String[] arguments) {
        List<String> optionsFromUser = new ArrayList<>();
        Map<String, List<String>> optionsAndLocationsFromUser = new HashMap<>();

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(requiredOptions, arguments);

            for (Option o : requiredOptions.getOptions()) {
                if (commandLine.hasOption(o)) {
                    optionsFromUser.add(o.getLongOpt());
                }
            }

            List<String> locations = new ArrayList<>(Arrays.asList(commandLine.getArgs()));

            optionsAndLocationsFromUser.putAll(Map.ofEntries(
                    Map.entry("options", optionsFromUser),
                    Map.entry("locations", locations)
            ));
        } catch (ParseException e) {
            String v = e.getMessage()
                    .substring(e.getMessage().indexOf(":") + 1)
                    .trim()
                    .replace("-", "");

            for (String s : v.split("")) {
                if (!requiredOptions.hasOption(s)) {
                    System.out.printf("wordtally: invalid option -- '%s'%nTry 'wordtally -h|--help' for more information.%n", s);
                }
            }

            System.exit(0);
        }

        return optionsAndLocationsFromUser;
    }

    @Override
    public List<String> checkFilesAvailability(List<String> locations) {
        List<String> availableFiles = new ArrayList<>();

        locations.parallelStream().forEach(f -> {
            if (Files.notExists(Path.of(f))) {
                System.out.printf("wordtally: %s: No such file or directory%n", f);
            } else {
                availableFiles.add(f);
            }
        });

        return availableFiles;
    }

    private void constructOutputToPrint(List<Long> results, String fileToPrint, boolean toPrintLocation) {
        results.forEach(e -> System.out.printf("%-7s", e));

        if (toPrintLocation) {
            System.out.printf("%s%n", fileToPrint);
        } else {
            System.out.println();
        }
    }

    private void calculateTotalIfMultipleFilesAndPrint(List<List<Long>> givenValuesFromCounter, int nCol) {
        List<Long> calcTotal = new ArrayList<>(givenValuesFromCounter.get(0));

        for (int j = 1; j < givenValuesFromCounter.size(); j++) {
            for (int i = 0; i < nCol; i++)
                calcTotal.set(i, givenValuesFromCounter.get(j).get(i) + calcTotal.get(i));
        }

        calcTotal.forEach(e -> System.out.printf("%-7s", e));
        System.out.printf("%-7s%n", "total");
    }

    @Override
    public void runTasksFromInput(String[] arguments, InputStream inputStream) {
        try {
            Map<String, List<String>> tasksAndFiles = extractTypeOfTasksAndLocationsFromInput(arguments);
            List<String> options = tasksAndFiles.get("options"), locations = tasksAndFiles.get("locations");
            Map<String, CompletableFuture<List<Long>>> results = new HashMap<>();

            if (!locations.isEmpty()) {
                List<String> filesToBeProcess = checkFilesAvailability(locations);

                for (String f : filesToBeProcess) {
                    Path file = Path.of(f);

                    CompletableFuture<List<Long>> cfT =
                            CompletableFuture.supplyAsync(() -> executeTasks(options, file));
                    results.put(file.toString(), cfT);
                }

                Map<String, List<Long>> rs = CompletableFuture.allOf(results.values().toArray(e -> new CompletableFuture[]{}))
                        .thenApplyAsync(w -> results.entrySet().stream()
                                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().join()))).join();

                for (Map.Entry<String, List<Long>> e : rs.entrySet()) {
                    constructOutputToPrint(e.getValue(), e.getKey(), true);
                }

                if (locations.size() > 1) {
                    calculateTotalIfMultipleFilesAndPrint(
                            new ArrayList<>(rs.values()),
                            options.isEmpty() ? 3 : options.size()
                    );
                }
            } else {
                options.forEach(e -> {
                    if ("help".equals(e)) {
                        printHelp(requiredOptions);
                    }
                });

                try {
                    if (!parsingAsAService.checkTheReaderIsReady(inputStream)) {
                        System.out.printf("wordtally: no input provided%nTry 'wordtally -h|--help' for more information.%n");
                        System.exit(0);
                    }

                } catch (IOException e) {
                    ErrorMessage msg = ErrorMessage.builder()
                            .threadName(Thread.currentThread().getName())
                            .clazzName(this.getClass().getName())
                            .message(e.getMessage())
                            .severity(Severity.ERROR)
                            .methodName("runTasksFromInput")
                            .dateTime(Instant.now().toString())
                            .build();

                    System.out.printf("%s %n", outputFormat.withJSONStyle().writeValueAsString(msg));
                    System.exit(0);
                }

                List<Long> r = processingAsAService.execTheTasksWithCountingInParallelWithParallelStreams(options, inputStream);
                constructOutputToPrint(r, null, false);
            }

        } catch (JsonProcessingException e) {
            ErrorMessage msg = ErrorMessage.builder()
                    .message(e.getMessage())
                    .dateTime(Instant.now().toString())
                    .clazzName(this.getClass().toString())
                    .methodName("decideTypeOfTaskForInputFromDescriptor")
                    .severity(Severity.ERROR)
                    .threadName(Thread.currentThread().getName())
                    .build();

            try {
                System.out.printf("%s%n", outputFormat.withJSONStyle().writeValueAsString(msg));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<Long> executeTasks(List<String> options, Path inputFile) {
        CompletableFuture<Long> cfnL, cfnW, cfnC, cfnB;
        List<CompletableFuture<Long>> allCFs = new ArrayList<>();

        if (options.isEmpty()) {
            cfnL = parsingAsAService.countTheNumberOfLines(inputFile);
            cfnW = parsingAsAService.countTheNumberOfWords(inputFile);
            cfnB = parsingAsAService.countTheNumberOfBytes(inputFile);

            allCFs.addAll(Arrays.asList(cfnL, cfnW, cfnB));
        } else {
            for (String s : options) {
                switch (s) {
                    case "help" -> printHelp(requiredOptions);
                    case "lines" -> {
                        cfnL = parsingAsAService.countTheNumberOfLines(inputFile);
                        allCFs.add(cfnL);
                    }
                    case "words" -> {
                        cfnW = parsingAsAService.countTheNumberOfWords(inputFile);
                        allCFs.add(cfnW);
                    }
                    case "chars" -> {
                        cfnC = parsingAsAService.countTheNumberOfChars(inputFile);
                        allCFs.add(cfnC);
                    }
                    case "bytes" -> {
                        cfnB = parsingAsAService.countTheNumberOfBytes(inputFile);
                        allCFs.add(cfnB);
                    }
                }
            }
        }

        return CompletableFuture.allOf(allCFs.toArray(e -> new CompletableFuture[]{}))
                .thenApply(v -> allCFs.stream()
                        .map(CompletableFuture::join)
                        .toList()).join();
    }
}
