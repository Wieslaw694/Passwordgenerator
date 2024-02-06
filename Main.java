import java.security.SecureRandom;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

    public static String generatePassword(int length) {
        if (length < 8 || length > 16) {
            throw new IllegalArgumentException("Heslo musí mít délku mezi 8 a 16 znaky.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String allCharacters = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + DIGITS + SPECIAL_CHARACTERS;

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

    private static void exportDoSouboru(String cestaKsouboru, String[] vysledky) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cestaKsouboru))) {
            // Zapisujeme výsledky do souboru
            for (String vysledek : vysledky) {
                writer.write(vysledek);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "Windows-1250");
        System.out.printf("Zadejte číslo v rozsahu 8 až 16: ");
        String hodnota = scanner.nextLine();
        int number = Integer.parseInt(hodnota);

        int passwordLength = number; // můžete změnit délku hesla podle potřeby
        String generatedPassword = generatePassword(passwordLength);
        System.out.println("Vygenerované heslo: " + generatedPassword);

        String[] vysledky = {generatedPassword};

        // Zadejte cestu k souboru, do kterého chcete exportovat výsledky
        String cestaKsouboru = "C:\\" + generatedPassword + ".txt";

        exportDoSouboru(cestaKsouboru, vysledky);

        System.out.println("Heslo bylo uloženo do názvu souboru i do souboru ;-)");
    }
}
