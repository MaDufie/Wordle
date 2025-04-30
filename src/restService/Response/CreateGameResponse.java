package restService.Response;

import DomainObjects.GameDomainObject;
import DomainObjects.GuessDomainObject;
import java.util.List;

public class CreateGameResponse {

    public boolean success;
    public int gameId;
    public int playerId;
    public int gameTypeId;
    public int wordLength;
    public String status;
    public boolean didPlayerWin;

    public List<GuessDomainObject> guesses;
    public List<String> errorMessages;

    public CreateGameResponse(GameDomainObject game) {
        this.success = true;
        this.gameId = game.gameId;
        this.playerId = game.playerId;
        this.gameTypeId = game.gameTypeId;
        this.wordLength = game.hiddenWord.length();
        this.status = game.status;
        this.didPlayerWin = game.didPlayerWin;
        this.guesses = game.guesses;
    }

    public CreateGameResponse(List<String> errorMessages) {
        this.success = false;
        this.errorMessages = errorMessages;
        this.gameId = -1;
        this.playerId = -1;
        this.gameTypeId = -1;
        this.wordLength = -1;

    }

    public boolean getSuccess() {
        return this.success;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getGameTypeId() {
        return this.gameTypeId;
    }

    public int getWordLength() {
        return this.wordLength;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean getDidPlayerWin() {
        return this.didPlayerWin;
    }

    public List<GuessDomainObject> getGuesses() {
        return this.guesses;
    }
}
