package DomainObjects;

import DataObjects.GameDataObject;
import DomainModels.GameTypeDomainModel;
import DomainModels.GuessDomainModel;
import DomainModels.PlayerDomainModel;
import DomainModels.WordLengthDomainModel;
import java.util.ArrayList;
import java.util.List;

public class GameDomainObject {

    public int gameId;
    public int playerId;
    public int gameTypeId;
    public int wordLengthId;
    public String hiddenWord;

    private PlayerDomainObject player;
    private GameTypeDomainObject gameType;
    private WordLengthDomainObject wordLength;

    public boolean isGameComplete;
    public boolean didPlayerWin;
    public int numGuesses;
    public String status;

    public List<GuessDomainObject> guesses;

    public GameDomainObject(GameDataObject gameDataObject) {
        this.gameId = gameDataObject.gameId;
        this.playerId = gameDataObject.playerId;
        this.gameTypeId = gameDataObject.gameTypeId;
        this.wordLengthId = gameDataObject.wordLengthId;
        this.hiddenWord = gameDataObject.hiddenWord;

        this.isGameComplete = gameDataObject.isGameComplete;
        this.didPlayerWin = gameDataObject.didPlayerWin;
        this.numGuesses = gameDataObject.numGuesses;
        this.status = gameDataObject.status;

    }

    public static List<GameDomainObject> MapList(List<GameDataObject> dataList) {
        List<GameDomainObject> domainList = new ArrayList<>();
        for (GameDataObject data : dataList) {
            domainList.add(new GameDomainObject(data));
        }
        return domainList;
    }

    public List<GuessDomainObject> GetGuesses() {
        if (this.guesses == null)
            this.guesses = GuessDomainModel.GetGuesses(this.gameId);
        return this.guesses;
    }

    public PlayerDomainObject GetPlayer() {
        if (player == null) {
            player = PlayerDomainModel.GetPlayerById(playerId);
        }
        return player;
    }

    public GameTypeDomainObject GetGameType() {
        if (gameType == null) {
            gameType = GameTypeDomainModel.GetGameTypeById(gameTypeId);
        }
        return gameType;
    }

    public WordLengthDomainObject GetWordLength() {
        if (wordLength == null) {
            wordLength = WordLengthDomainModel.GetWordLengthById(wordLengthId);
        }
        return wordLength;
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

    public int getWordLengthId() {
        return this.wordLengthId;
    }

    public boolean getDidPlayerWin() {
        return this.didPlayerWin;
    }

    public boolean getIsGameComplete() {
        return this.isGameComplete;
    }

    public int getNumGuesses() {
        return this.numGuesses;
    }
}
