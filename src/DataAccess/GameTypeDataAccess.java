package DataAccess;

import java.util.HashMap;
import DataObjects.GameTypeDataObject;

public class GameTypeDataAccess {
    private static final HashMap<Integer, GameTypeDataObject> gameTypes = new HashMap<>();

    static {
        GameTypeDataObject normal = new GameTypeDataObject(1, "Normal", 6);
        gameTypes.put(normal.gameTypeId, normal);

        GameTypeDataObject hard = new GameTypeDataObject(2, "Hard", 6);
        gameTypes.put(hard.gameTypeId, hard);
    }

    public static GameTypeDataObject GetGameTypeById(int gameTypeId) {
        return gameTypes.get(gameTypeId);
    }
}
