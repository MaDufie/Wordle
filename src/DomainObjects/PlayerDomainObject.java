package DomainObjects;

import java.util.List;

import DataObjects.PlayerDataObject;
import DomainModels.GameDomainModel;

public class PlayerDomainObject {
    public int playerId;
    public String firstName;
    public String lastName;
    public String email;
    public int userId;

    private List<GameDomainObject> games;

    public PlayerDomainObject(PlayerDataObject playerDataObject) {
        this.playerId = playerDataObject.playerId;
        this.firstName = playerDataObject.firstName;
        this.lastName = playerDataObject.lastName;
        this.email = playerDataObject.email;
        this.userId = playerDataObject.userId;
    }

    public List<GameDomainObject> GetGames() {
        // Lazy load the Games
        if (this.games == null)
            this.games = GameDomainModel.GetGamesByPlayerId(this.playerId);
        return this.games;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getUserId() {
        return this.userId;
    }
}
