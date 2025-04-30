package DataObjects;

public class GuessDataObject {
    public int guessId;
    public int gameId;
    public int ordinal;
    public String guessedWord;
    public String result;

    public GuessDataObject(int guessId, int gameId, int ordinal, String guessedWord, String result) {
        this.guessId = guessId;
        this.gameId = gameId;
        this.ordinal = ordinal;
        this.guessedWord = guessedWord;
        this.result = result;
    }

    public GuessDataObject(GuessDataObject copy) {
        this.guessId = copy.guessId;
        this.gameId = copy.gameId;
        this.ordinal = copy.ordinal;
        this.guessedWord = copy.guessedWord;
        this.result = copy.result;
    }
}
