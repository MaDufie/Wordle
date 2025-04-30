package DomainModels;

import DataAccess.GuessDataAccess;
import DataObjects.GuessDataObject;
import DomainObjects.GuessDomainObject;

import java.util.List;

public class GuessDomainModel {

    public static GuessDataObject CreateGuess(int gameId, int ordinal, String guessedWord, String result) {
        return GuessDataAccess.CreateNewGuess(gameId, ordinal, guessedWord, result);
    }

    public static List<GuessDomainObject> GetGuesses(int gameId) {
        List<GuessDataObject> guessDataObjects = GuessDataAccess.GetGuessesByGameId(gameId);
        return GuessDomainObject.MapList(guessDataObjects);
    }
}
