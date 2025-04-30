package DataAccess;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import DataObjects.GuessDataObject;

public class GuessDataAccess {
    private static final HashMap<Integer, List<GuessDataObject>> guessesByGameId = new HashMap<>();
    private static int nextGuessId = 1;

    private static synchronized int reserveNextGuessId() {
        return nextGuessId++;
    }

    public static GuessDataObject CreateNewGuess(int gameId, int ordinal, String guessedWord, String result) {
        int guessId = reserveNextGuessId();

        GuessDataObject newGuess = new GuessDataObject(guessId, gameId, ordinal, guessedWord, result);

        guessesByGameId.putIfAbsent(gameId, new ArrayList<>());
        guessesByGameId.get(gameId).add(newGuess);

        return new GuessDataObject(newGuess);
    }

    public static List<GuessDataObject> GetGuessesByGameId(int gameId) {
        return guessesByGameId.getOrDefault(gameId, new ArrayList<>());
    }
}
