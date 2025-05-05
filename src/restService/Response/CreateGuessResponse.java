package restService.Response;

public class CreateGuessResponse {
    public boolean success;
    public String result;
    public boolean didPlayerWin;
    public boolean isGameComplete;
    public int numGuesses;

    public String errorMessage;

    public CreateGuessResponse(String result, boolean didPlayerWin, boolean isGameComplete, int numGuesses) {
        this.success = true;
        this.result = result;
        this.didPlayerWin = didPlayerWin;
        this.isGameComplete = isGameComplete;
        this.numGuesses = numGuesses;
    }

    public CreateGuessResponse(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getResult() {
        return this.result;
    }

    public boolean getIsGameComplete() {
        return this.isGameComplete;
    }

    public boolean getDidPlayerWin() {
        return this.didPlayerWin;
    }

    public int getNumGuesses() {
        return this.numGuesses;
    }
}
