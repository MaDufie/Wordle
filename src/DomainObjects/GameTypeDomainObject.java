package DomainObjects;

import DataObjects.GameTypeDataObject;

public class GameTypeDomainObject {
    public int gameTypeId;
    public String description;
    public int numGuesses;

    public GameTypeDomainObject(GameTypeDataObject gameTypeDataObject) {
        this.gameTypeId = gameTypeDataObject.gameTypeId;
        this.description = gameTypeDataObject.description;
        this.numGuesses = gameTypeDataObject.numGuesses;
    }

    public int getGameTypeId() {
        return this.gameTypeId;
    }

    public String getDescription() {
        return this.description;
    }

    public int getNumGuesses() {
        return this.numGuesses;
    }
}
