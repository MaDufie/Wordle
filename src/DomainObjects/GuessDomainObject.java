package DomainObjects;

import DataObjects.GuessDataObject;
import java.util.ArrayList;
import java.util.List;

public class GuessDomainObject {
    public int guessId;
    public int gameId;
    public int ordinal;
    public String guessedWord;
    public String result;

    public GuessDomainObject(GuessDataObject guessDataObject) {
        this.guessId = guessDataObject.guessId;
        this.gameId = guessDataObject.gameId;
        this.ordinal = guessDataObject.ordinal;
        this.guessedWord = guessDataObject.guessedWord;
        this.result = guessDataObject.result;
    }

    public static List<GuessDomainObject> MapList(List<GuessDataObject> guessDataObjects) {
        List<GuessDomainObject> guessDomainObjects = new ArrayList<>(guessDataObjects.size());
        for (GuessDataObject guessDataObject : guessDataObjects) {
            guessDomainObjects.add(new GuessDomainObject(guessDataObject));
        }
        return guessDomainObjects;
    }

    public int getGuessId() {
        return this.guessId;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public String getGuessedWord() {
        return this.guessedWord;
    }

    public String getResult() {
        return this.result;
    }
}
