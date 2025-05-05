package DomainModels;

import Common.ValidationException;
import Common.WordSelector;
import DataAccess.GameDataAccess;
import DataObjects.GameDataObject;
import DomainObjects.GameDomainObject;
import java.util.ArrayList;
import java.util.List;

public class GameDomainModel {

    public static GameDomainObject GetGameById(int gameId) {
        GameDataObject gameDataObject = GameDataAccess.GetGameById(gameId);
        return new GameDomainObject(gameDataObject);
    }

    public static List<GameDomainObject> GetAllGames() {
        List<GameDataObject> gameDataObjects = GameDataAccess.GetAllGames();
        return GameDomainObject.MapList(gameDataObjects);
    }

    public static List<GameDomainObject> GetGamesByPlayerId(int playerId) {
        List<GameDataObject> gameDataObjects = GameDataAccess.GetGamesByPlayerId(playerId);
        return GameDomainObject.MapList(gameDataObjects);
    }

    public static GameDomainObject createNewGame(int playerId, int gameTypeId) throws ValidationException {
        ArrayList<String> errors = new ArrayList<>();

        if (!PlayerDomainModel.ValidatePlayerId(playerId)) {
            errors.add("playerId does not exist");
        }

        if (!GameTypeDomainModel.ValidateGameTypeId(gameTypeId)) {
            errors.add("Unsupported gameTypeId");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        int wordLengthId = 1;
        String hiddenWord = WordSelector.getRandomWord();

        GameDataObject game = GameDataAccess.CreateNewGame(
                playerId,
                gameTypeId,
                wordLengthId,
                hiddenWord);

        return new GameDomainObject(game);
    }
}
