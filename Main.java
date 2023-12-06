import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Define the Space Person alphabet
    private static final Map<Character, Character> spacePersonAlphabet = new HashMap<>();
    static {
        // Initialize the Space Person alphabet mapping
        spacePersonAlphabet.put('a', '~');
        spacePersonAlphabet.put('b', '!');
        spacePersonAlphabet.put('c', '@');
        spacePersonAlphabet.put('d', '#');
        spacePersonAlphabet.put('e', '$');
        spacePersonAlphabet.put('f', '%');
        spacePersonAlphabet.put('g', '^');
        spacePersonAlphabet.put('h', '&');
        spacePersonAlphabet.put('i', '*');
        spacePersonAlphabet.put('j', '(');
        spacePersonAlphabet.put('k', ')');
        spacePersonAlphabet.put('l', '-');
        spacePersonAlphabet.put('m', '_');
        spacePersonAlphabet.put('n', '=');
        spacePersonAlphabet.put('o', '+');
        spacePersonAlphabet.put('p', '[');
        spacePersonAlphabet.put('q', ']');
        spacePersonAlphabet.put('r', '{');
        spacePersonAlphabet.put('s', '}');
        spacePersonAlphabet.put('t', ';');
        spacePersonAlphabet.put('u', ':');
        spacePersonAlphabet.put('v', ',');
        spacePersonAlphabet.put('w', '.');
        spacePersonAlphabet.put('x', '<');
        spacePersonAlphabet.put('y', '>');
        spacePersonAlphabet.put('z', '?');
        // Add the remaining characters of the alphabet
        for (char c = 'a'; c <= 'z'; c++) {
            if (!spacePersonAlphabet.containsKey(c)) {
                spacePersonAlphabet.put(c, c);
            }
        }
    }

    // Convert English string to Space Person string
    private static String convertToSpacePerson(String inputString) {
        StringBuilder spacePersonString = new StringBuilder();
        // Loop through each character in the input string
        for (char c : inputString.toLowerCase().toCharArray()) {
            // Check if the character has a Space Person mapping
            if (spacePersonAlphabet.containsKey(c)) {
                // Append the Space Person character if mapping exists
                spacePersonString.append(spacePersonAlphabet.get(c));
            } else {
                // Append the original character if no mapping exists
                spacePersonString.append(c);
            }
        }
        // Return the final Space Person string
        return spacePersonString.toString();
    }

    // Calculate SHA256 hash
    private static String calculateSHA256(String inputString) throws NoSuchAlgorithmException {
        // Create a MessageDigest instance for SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // Calculate the hash value for the input string
        byte[] hashBytes = md.digest(inputString.getBytes());
        StringBuilder hashValue = new StringBuilder();

        // Convert each byte in the hash to a hexadecimal string
        for (byte b : hashBytes) {
            hashValue.append(String.format("%02x", b));
        }

        // Return the final SHA-256 hash value
        return hashValue.toString();
    }

    // Perform Caesar cipher with a 5-character shift
    private static String caesarCipher(String inputString) {
        StringBuilder result = new StringBuilder();
        // Loop through each character in the input string
        for (char c : inputString.toLowerCase().toCharArray()) {
            // Check if the character is alphabetic
            if (Character.isAlphabetic(c)) {
                // Perform Caesar cipher shift and append the result
                char shiftedChar = (char) (((c - 'a' + 5) % 26) + 'a');
                result.append(shiftedChar);
            } else {
                // Append the non-alphabetic character unchanged
                result.append(c);
            }
        }
        // Return the final Caesar cipher result
        return result.toString();
    }

    // Brute force all 0-25 shifts
    private static Map<Integer, String> bruteForceCaesarCipher(String inputString) {
        Map<Integer, String> results = new HashMap<>();
        // Loop through all possible shifts (0 to 25)
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder shiftedText = new StringBuilder();
            // Loop through each character in the input string
            for (char c : inputString.toLowerCase().toCharArray()) {
                // Check if the character is alphabetic
                if (Character.isAlphabetic(c)) {
                    // Perform Caesar cipher shift based on the current shift value
                    char shiftedChar = (char) (((c - 'a' + shift) % 26) + 'a');
                    shiftedText.append(shiftedChar);
                } else {
                    // Append the non-alphabetic character unchanged
                    shiftedText.append(c);
                }
            }
            // Store the result for the current shift value
            results.put(shift, shiftedText.toString());
        }
        // Return the map of brute force results
        return results;
    }

    public static void main(String[] args) {
        // Create a Scanner for user input
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input
            System.out.print("Enter an English string: ");
            String englishString = scanner.nextLine();

            // Convert to Space Person string
            String spacePersonString = convertToSpacePerson(englishString);
            System.out.println("Space Person String: " + spacePersonString);

            try {
                // Calculate SHA256 hash
                String hashValue = calculateSHA256(spacePersonString);
                System.out.println("SHA256 Hash Value: " + hashValue);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Error calculating SHA256 hash: " + e.getMessage());
            }

            // Caesar cipher with a 5-character shift
            String caesarCipherText = caesarCipher(englishString);
            System.out.println("Caesar Cipher (5-character shift): " + caesarCipherText);

            // Brute force all 0-25 shifts
            Map<Integer, String> bruteForceResults = bruteForceCaesarCipher(englishString);
            System.out.println("Brute Force Caesar Cipher Results:");
            for (Map.Entry<Integer, String> entry : bruteForceResults.entrySet()) {
                System.out.println("Shift " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}