package Common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WordSelector {
    private static final List<String> wordBank = new ArrayList<>();
    private static final Random random = new Random();

    static {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(
                                    WordSelector.class.getResourceAsStream("./resources/wordlist.txt"))));
            String line;
            while ((line = reader.readLine()) != null) {
                wordBank.add(line.trim().toLowerCase());
            }
        } catch (Exception e) {
            System.err.println("Error loading word list: " + e.getMessage());
        }
    }

    public static String getRandomWord() {
        return wordBank.get(random.nextInt(wordBank.size()));
    }
}
