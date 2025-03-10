
package playfair;

import java.util.Scanner;
import java.util.HashSet;//for duplicate char

public class Playfair {
    public static char[][] keyword(String key){
        key=key.toUpperCase().replace("J", "I");
         HashSet<Character> usedkey=new HashSet<>();
         StringBuilder uniqueKey = new StringBuilder();
          for (char c : key.toCharArray()) {
            
            if (!usedkey.contains(c)) {
                usedkey.add(c); 
                uniqueKey.append(c); 
            }
        }
           String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";//A->Z
        for (char c : alphabet.toCharArray()) {
           
            if (!usedkey.contains(c)) {
                uniqueKey.append(c); 
            }
        }
        char playfairSquare[][] = new char[5][5];
        int index = 0; 

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                playfairSquare[row][col] = uniqueKey.charAt(index); 
                index++; 
            }
        }

        return playfairSquare; 
    }
   public static void displayMatrix(char[][] square) {
        System.out.println("Playfair Square:");
        for (int row = 0; row < 5; row++) {
            
            for (int col = 0; col < 5; col++) {
                System.out.print(square[row][col] + " "); 
            }
            System.out.println(); 
        }
    }    
       
   // handle the space problem
   public static String processKeyword(String keyword) {
           
        return keyword.replace(" ", "");
    }    
   // handle the text (remove any space J -> I)  
   public static String prepareText(String text) {
        text = text.toUpperCase().replace(" ", ""); 
        text = text.replace("J", "I");
        if (text.length() % 2 != 0) {
            text += "X";
        }
        return text;
    }
   public static int[] findPosition(char[][] square, char c) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (square[row][col] == c) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1}; // Character not found
    }
    public static String encrypt(String plaintext, char[][] square) {
        plaintext = prepareText(plaintext); // Prepare the plaintext
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);

            int[] posA = findPosition(square, a);
            int[] posB = findPosition(square, b);

             if (posA[0] == posB[0]) { // Same row
                ciphertext.append(square[posA[0]][(posA[1] + 1) % 5]);
                ciphertext.append(square[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                ciphertext.append(square[(posA[0] + 1) % 5][posA[1]]);
                ciphertext.append(square[(posB[0] + 1) % 5][posB[1]]);
            } else { // Rectangle
                ciphertext.append(square[posA[0]][posB[1]]);
                ciphertext.append(square[posB[0]][posA[1]]);
            }
        }

        return ciphertext.toString();
    }
  public static String decrypt(String ciphertext, char[][] square) {
        ciphertext = prepareText(ciphertext); // Prepare the ciphertext
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int[] posA = findPosition(square, a);
            int[] posB = findPosition(square, b);

            if (posA[0] == posB[0]) { // Same row
                plaintext.append(square[posA[0]][(posA[1] - 1 + 5) % 5]);
                plaintext.append(square[posB[0]][(posB[1] - 1 + 5) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                plaintext.append(square[(posA[0] - 1 + 5) % 5][posA[1]]);
                plaintext.append(square[(posB[0] - 1 + 5) % 5][posB[1]]);
            } else { // Rectangle
                plaintext.append(square[posA[0]][posB[1]]);
                plaintext.append(square[posB[0]][posA[1]]);
            }
        }

        return plaintext.toString();
    }
    
   
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

       

        // Step 1: Ask the user to input the keyword
        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine();
        keyword = processKeyword(keyword); // Remove spaces

        // Step 2: Generate the Playfair square
        char[][] playfairSquare = keyword(keyword);
        displayMatrix(playfairSquare);

        // Step 3: Ask the user to choose between encryption and decryption
        System.out.print("Do you want to encrypt or decrypt? (e/d): ");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("e")) {
            // Encryption
            System.out.print("Enter the plaintext: ");
            String plaintext = scanner.nextLine();
            String ciphertext = encrypt(plaintext, playfairSquare);
            System.out.println("Encrypted text: " + ciphertext);
        } else if (choice.equals("d")) {
            // Decryption
            System.out.print("Enter the ciphertext: ");
            String ciphertext = scanner.nextLine();
            String plaintext = decrypt(ciphertext, playfairSquare);
            System.out.println("Decrypted text: " + plaintext);
        } else {
            System.out.println("Invalid choice!");
        }
        
    }
    
}
