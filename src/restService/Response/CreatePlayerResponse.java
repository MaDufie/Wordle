package restService.Response;

import DomainObjects.PlayerDomainObject;
import java.util.List;

public class CreatePlayerResponse {
    public boolean success;
    public int playerId;
    public String firstName;
    public String lastName;
    public String email;
    public int userId;
    public List<String> errorMessages;

    public CreatePlayerResponse(PlayerDomainObject player) {
        this.success = true;
        this.playerId = player.playerId;
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.email = player.email;
        this.userId = player.userId;
    }

    public CreatePlayerResponse(List<String> errorMessages) {
        this.success = false;
        this.errorMessages = errorMessages;
    }

    public boolean getSuccess() {
        return this.success;
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

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }
}
