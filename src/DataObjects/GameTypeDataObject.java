package DataObjects;

public class GameTypeDataObject {
    public int gameTypeId;
    public String description;
    public int numGuesses;

    public GameTypeDataObject(int gameTypeId, String description, int numGuesses) {
        this.gameTypeId = gameTypeId;
        this.description = description;
        this.numGuesses = numGuesses;
    }

    public GameTypeDataObject(GameTypeDataObject copy) {
        this.gameTypeId = copy.gameTypeId;
        this.description = copy.description;
        this.numGuesses = copy.numGuesses;
    }
}
