package restService.Request;

public class CreateGuessRequest {
    public int gameId;
    public String guessedWord;

    public CreateGuessRequest(int gameId, String guessedWord) {
        this.gameId = gameId;
        this.guessedWord = guessedWord;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGuessedWord() {
        return guessedWord;
    }
}
