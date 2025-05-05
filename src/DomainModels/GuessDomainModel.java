package DomainModels;

import Common.ValidationException;
import DataAccess.GameDataAccess;
import DataAccess.GuessDataAccess;
import DataObjects.GameDataObject;
import DataObjects.GuessDataObject;
import DomainObjects.GuessDomainObject;
import java.util.ArrayList;
import java.util.List;

public class GuessDomainModel {

    public static GuessDomainObject submitGuess(int gameId, String guessedWord) throws ValidationException {
        List<String> errors = new ArrayList<>();

        GameDataObject game = fetchAndValidateGame(gameId, errors);
        validateWordLength(guessedWord, game.hiddenWord.length(), errors);

        // Enforce hard mode and store invalid guess
        if (game.gameTypeId == 2 && errors.isEmpty()) {
            enforceHardMode(game, guessedWord, errors);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        String rawResult = computeResult(guessedWord, game.hiddenWord);
        int ordinal = getNextGuessOrdinal(game.gameId);
        GuessDataObject guess = GuessDataAccess.CreateNewGuess(game.gameId, ordinal, guessedWord, rawResult);

        updateGameState(game, guessedWord);
        GameDataAccess.UpdateGame(game);

        GuessDomainObject domainGuess = new GuessDomainObject(guess);
        return domainGuess;
    }

    private static GameDataObject fetchAndValidateGame(int gameId, List<String> errors) throws ValidationException {
        GameDataObject game = GameDataAccess.GetGameById(gameId);
        if (game == null)
            errors.add("Invalid gameId");
        else if (game.isGameComplete)
            errors.add("Game is already complete");

        if (!errors.isEmpty())
            throw new ValidationException(errors);
        return game;
    }

    private static void validateWordLength(String word, int expectedLength, List<String> errors) {
        if (word.length() != expectedLength) {
            errors.add("Guess must be " + expectedLength + " letters long.");
        }
    }

    private static void enforceHardMode(GameDataObject game, String guessedWord, List<String> errors) {
        List<GuessDataObject> allGuesses = GuessDataAccess.GetGuessesByGameId(game.gameId);
        List<GuessDataObject> validGuesses = allGuesses.stream().filter(g -> g.isValid).toList();

        if (!validGuesses.isEmpty()) {
            GuessDataObject lastValid = validGuesses.get(validGuesses.size() - 1);
            String prevWord = lastValid.guessedWord;
            String result = lastValid.result;

            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == 'G' && guessedWord.charAt(i) != prevWord.charAt(i)) {
                    // Hard mode violation: save invalid guess and update game
                    String rawResult = computeResult(guessedWord, game.hiddenWord);
                    int ordinal = getNextGuessOrdinal(game.gameId);
                    GuessDataObject guess = GuessDataAccess.CreateNewGuess(game.gameId, ordinal, guessedWord,
                            rawResult);
                    guess.isValid = false;
                    GuessDataAccess.UpdateGuess(guess);

                    game.numGuesses++;
                    if (game.numGuesses >= 6) {
                        game.didPlayerWin = false;
                        game.isGameComplete = true;
                        game.status = "Complete";
                    }
                    GameDataAccess.UpdateGame(game);

                    errors.add("Hard Mode: All correct letters must stay in the same position.");
                    return;
                }
            }
        }
    }

    private static int getNextGuessOrdinal(int gameId) {
        return GuessDataAccess.GetGuessesByGameId(gameId).size() + 1;
    }

    private static void updateGameState(GameDataObject game, String guessedWord) {
        game.numGuesses++;
        if (guessedWord.equalsIgnoreCase(game.hiddenWord)) {
            game.didPlayerWin = true;
            game.isGameComplete = true;
            game.status = "Complete";
        } else if (game.numGuesses >= 6) {
            game.didPlayerWin = false;
            game.isGameComplete = true;
            game.status = "Complete";
        }

        List<GuessDataObject> allGuesses = GuessDataAccess.GetGuessesByGameId(game.gameId);
        game.guesses = allGuesses;
    }

    private static String computeResult(String guess, String target) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c == target.charAt(i)) {
                result.append("G");
            } else if (target.contains(String.valueOf(c))) {
                result.append("Y");
            } else {
                result.append("*");
            }
        }
        return result.toString();
    }

    public static List<GuessDomainObject> GetGuesses(int gameId) {
        List<GuessDataObject> guessDataObjects = GuessDataAccess.GetGuessesByGameId(gameId);
        return GuessDomainObject.MapList(guessDataObjects);
    }
}