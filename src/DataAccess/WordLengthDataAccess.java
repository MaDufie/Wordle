package DataAccess;

import java.util.HashMap;
import DataObjects.WordLengthDataObject;

public class WordLengthDataAccess {
    private static final HashMap<Integer, WordLengthDataObject> wordLengths = new HashMap<>();

    static {
        WordLengthDataObject wl5 = new WordLengthDataObject(1, "Standard", 5);
        wordLengths.put(wl5.wordLengthId, wl5);
    }

    public static WordLengthDataObject GetWordLengthById(int wordLengthId) {
        return wordLengths.get(wordLengthId);
    }
}
