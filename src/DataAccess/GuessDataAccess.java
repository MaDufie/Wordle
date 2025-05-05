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
        return guessesByGameId.getOrDefault(gameId, new ArrayList<GuessDataObject>());
    }

    public static void UpdateGuess(GuessDataObject updatedGuess) {
        List<GuessDataObject> guesses = guessesByGameId.get(updatedGuess.gameId);
        for (int i = 0; i < guesses.size(); i++) {
            if (guesses.get(i).ordinal == updatedGuess.ordinal) {
                guesses.set(i, updatedGuess);
                break;
            }
        }
    }
}
