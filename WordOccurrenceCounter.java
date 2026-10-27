import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordOccurrenceCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text file name: ");
        String fileName = scanner.nextLine().trim();
        scanner.close();

        TreeMap<String, Integer> wordCounts = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Convert to lowercase and treat punctuation as word separators.
                String[] words = line.toLowerCase().split("[^\\p{L}\\p{N}']+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }

            if (wordCounts.isEmpty()) {
                System.out.println("The file contains no words.");
            } else {
                System.out.println("\\nWord occurrence count:");
                for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
