
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
        Map<Character, Integer> frequencyMap = new HashMap<>();
        // Convert the text to uppercase to handle lowercase letters
        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            // Check if the character is a letter (A-Z)
            if (c >= 'A' && c <= 'Z') {
                if (frequencyMap.containsKey(c)) {
                    frequencyMap.put(c, frequencyMap.get(c) + 1);
                } else {
                    frequencyMap.put(c, 1);
                }
            }
        }
        return frequencyMap;
    }
    public static String decryptText(String encryptedText, Map<Character, Integer> frequencyMap) {
        Map<Character, Character> replacementMap = new HashMap<>();
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        for (int i = 0; i < entries.size() && i < COMMON_LETTERS.length; i++) {
            replacementMap.put(entries.get(i).getKey(), COMMON_LETTERS[i]);
        }
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the encrypted text: ");
        String encryptedText = scanner.nextLine();
        Map<Character, Integer> frequencyMap = performFrequencyAnalysis(encryptedText);
        String decryptedText = decryptText(encryptedText, frequencyMap);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
