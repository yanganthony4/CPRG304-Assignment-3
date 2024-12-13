package implementations;

import java.io.*;
import java.util.*;
import utilities.Iterator;
import java.util.stream.Collectors;

public class WordTracker {

    private static final String REPOSITORY_FILE = "repository.ser";

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 4) {
            System.err.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");
            System.exit(1);
        }

        String inputFile = args[0];
        String option = args[1];
        String outputFile = args.length == 4 && args[2].equals("-f") ? args[3] : null;

        BSTree<String> wordTree = loadRepository();

        try {
            processFile(inputFile, wordTree);
        } catch (IOException e) {
            System.err.println("Error processing file: " + inputFile);
            e.printStackTrace();
        }

        saveRepository(wordTree);

        try {
            generateReport(wordTree, option, outputFile, inputFile);
        } catch (IOException e) {
            System.err.println("Error generating report.");
            e.printStackTrace();
        }
    }

    private static BSTree<String> loadRepository() {
        File repoFile = new File(REPOSITORY_FILE);
        if (!repoFile.exists()) {
            return new BSTree<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repoFile))) {
            return (BSTree<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading repository, starting with a new tree.");
            return new BSTree<>();
        }
    }

    private static void saveRepository(BSTree<String> wordTree) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
        } catch (IOException e) {
            System.err.println("Error saving repository.");
            e.printStackTrace();
        }
    }

    private static void processFile(String inputFile, BSTree<String> wordTree) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] words = line.split("[^\\w']+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        String entry = word + "@" + inputFile + ":" + lineNumber;
                        wordTree.add(entry);
                    }
                }
            }
        }
    }

    private static void generateReport(BSTree<String> wordTree, String option, String outputFile, String inputFile) throws IOException {
        Map<String, Map<String, List<Integer>>> wordMap = new TreeMap<>(); // TreeMap for sorting alphabetically
        Iterator<String> iterator = wordTree.inorderIterator();

        while (iterator.hasNext()) {
            String entry = iterator.next();
            String[] parts = entry.split("@");
            String word = parts[0];
            String[] details = parts[1].split(":");
            String fileName = details[0];
            int lineNumber = Integer.parseInt(details[1]);

            if (!option.equals("-po") && !fileName.equals(inputFile)) {
                continue; // Skip entries not matching the input file for -pl and -pf
            }

            wordMap.putIfAbsent(word, new HashMap<>());
            wordMap.get(word).putIfAbsent(fileName, new ArrayList<>());
            wordMap.get(word).get(fileName).add(lineNumber);
        }

        List<String> report = new ArrayList<>();
        for (Map.Entry<String, Map<String, List<Integer>>> wordEntry : wordMap.entrySet()) {
            String word = wordEntry.getKey();
            Map<String, List<Integer>> fileOccurrences = wordEntry.getValue();
            report.add(formatEntry(word, fileOccurrences, option));
        }

        if (outputFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (String line : report) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } else {
            System.out.println("Writing " + option + " format");
            for (String line : report) {
                System.out.println(line);
            }
            System.out.println("\nNot exporting file");
        }
    }

    private static String formatEntry(String word, Map<String, List<Integer>> fileOccurrences, String option) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word).append("===\t");

        for (Map.Entry<String, List<Integer>> entry : fileOccurrences.entrySet()) {
            String fileName = entry.getKey();
            List<Integer> lineNumbers = entry.getValue();

            result.append("found in file: ").append(fileName);

            if (option.equals("-pl") || option.equals("-po")) {
                result.append(" on lines: ");
                result.append(lineNumbers.stream().map(String::valueOf).distinct().collect(Collectors.joining(", ")));
            }

            if (option.equals("-po")) {
                result.append(" (occurrences: ").append(lineNumbers.size()).append(")");
            }

            result.append("\t");
        }

        return result.toString();
    }
}
