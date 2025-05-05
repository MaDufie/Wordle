package DataObjects;

import java.util.List;
import java.util.ArrayList;

public class GameDataObject {
    public int gameId;
    public int playerId;
    public int gameTypeId;
    public int wordLengthId;
    public String hiddenWord;

    public boolean isGameComplete;
    public boolean didPlayerWin;
    public int numGuesses;
    public String status;

    public List<GuessDataObject> guesses;

    public GameDataObject(int gameId, int playerId, int gameTypeId, int wordLengthId, String hiddenWord,
            boolean isGameComplete, boolean didPlayerWin, int numGuesses, String status,
            List<GuessDataObject> guesses) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameTypeId = gameTypeId;
        this.wordLengthId = wordLengthId;
        this.hiddenWord = hiddenWord;
        this.isGameComplete = isGameComplete;
        this.didPlayerWin = didPlayerWin;
        this.numGuesses = numGuesses;
        this.status = status;
        this.guesses = guesses != null ? new ArrayList<>(guesses) : new ArrayList<>();

    }

    public GameDataObject(GameDataObject copy) {
        this.gameId = copy.gameId;
        this.playerId = copy.playerId;
        this.gameTypeId = copy.gameTypeId;
        this.wordLengthId = copy.wordLengthId;
        this.hiddenWord = copy.hiddenWord;
        this.isGameComplete = copy.isGameComplete;
        this.didPlayerWin = copy.didPlayerWin;
        this.numGuesses = copy.numGuesses;
        this.status = copy.status;
        this.guesses = copy.guesses != null ? new ArrayList<>(copy.guesses) : new ArrayList<>();
    }
}
