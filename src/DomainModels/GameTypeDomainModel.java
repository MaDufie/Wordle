package DomainModels;

import DataAccess.GameTypeDataAccess;
import DataObjects.GameTypeDataObject;
import DomainObjects.GameTypeDomainObject;

public class GameTypeDomainModel {

    public static boolean ValidateGameTypeId(int gameTypeId) {
        GameTypeDataObject GameTypeDataObject = GameTypeDataAccess.GetGameTypeById(gameTypeId);
        if (GameTypeDataObject == null)
            return false;
        return true;
    }

    public static GameTypeDomainObject GetGameTypeById(int gameTypeId) {
        GameTypeDataObject gameTypeDataObject = GameTypeDataAccess.GetGameTypeById(gameTypeId);
        return new GameTypeDomainObject(gameTypeDataObject);
    }
}
