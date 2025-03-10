
package monobouns;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MonoBouns {
  private static final char[] COMMON_LETTERS = {
        'E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 
        'L', 'C', 'U', 'M', 'W', 'F', 'G', 'Y', 'B', 'K', 
        'X', 'Z', 'J', 'Q'
    };

     public static Map<Character, Integer> performFrequencyAnalysis(String text) {
        // Create a map to store the frequency of each letter
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Convert the text to uppercase to handle lowercase letters
        text = text.toUpperCase();

        // Loop through each character in the text
        for (char c : text.toCharArray()) {
            // Check if the character is a letter (A-Z)
            if (c >= 'A' && c <= 'Z') {
                // Update the frequency map
                if (frequencyMap.containsKey(c)) {
                    frequencyMap.put(c, frequencyMap.get(c) + 1);
                } else {
                    frequencyMap.put(c, 1);
                }
            }
        }

        return frequencyMap;
    }

    // Function to replace letters in the encrypted text based on frequency analysis
    public static String decryptText(String encryptedText, Map<Character, Integer> frequencyMap) {
        // Create a map to store the replacement mappings
        Map<Character, Character> replacementMap = new HashMap<>();

        // Convert the frequency map to a list of entries
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // Sort the entries by frequency (descending)
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Map the most frequent letters to the most common English letters
        for (int i = 0; i < entries.size() && i < COMMON_LETTERS.length; i++) {
            replacementMap.put(entries.get(i).getKey(), COMMON_LETTERS[i]);
        }

        // Decrypt the text by replacing letters
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                // Replace the letter if it exists in the replacement map
                if (replacementMap.containsKey(c)) {
                    decryptedText.append(replacementMap.get(c)); // Replace with mapped letter
                } else {
                    decryptedText.append(c); // Keep the original letter
                }
            } else {
                // Keep non-alphabetic characters as is
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Take encrypted text as input
        System.out.print("Enter the encrypted text: ");
        String encryptedText = scanner.nextLine();

        // Step 2: Perform frequency analysis
        Map<Character, Integer> frequencyMap = performFrequencyAnalysis(encryptedText);

        // Step 3: Decrypt the text by replacing letters based on frequency analysis
        String decryptedText = decryptText(encryptedText, frequencyMap);

        // Step 4: Output the decrypted text
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}