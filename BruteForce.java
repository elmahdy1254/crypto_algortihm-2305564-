//mohamed ahmed mohamed elmahdy
//2305564
package bruteforce;
import java.util.Scanner;

public class BruteForce {
    public static String decryptCaesar(String encryptedText, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { 
                char decryptedChar = (char) (((c - 'A' - shift + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);
            } else if (c >= 'a' && c <= 'z') { 
                char decryptedChar = (char) (((c - 'a' - shift + 26) % 26) + 'a');
                decryptedText.append(decryptedChar);
            } else {//if the index was non char (space,number,symbol....)
                
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
     }
   
      public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

        // Step 1: Take encrypted message as input
        System.out.print("Enter the encrypted message: ");
        String encryptedText = scanner.nextLine();

        // Step 2: Try all possible Caesar shifts (0 to 25)
        System.out.println("All possible decrypted texts:");
        for (int shift = 0; shift < 26; shift++) {
            String decryptedText = decryptCaesar(encryptedText, shift);
            System.out.println("Shift by "+ shift+" :"+ decryptedText);
        }
    
    
    
    
    }
    
}
