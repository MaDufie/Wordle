package DataObjects;

public class PlayerDataObject {
    public int playerId;
    public String firstName;
    public String lastName;
    public String email;
    public int userId;

    public PlayerDataObject(int playerId, String firstName, String lastName, String email, int userId) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
    }

    public PlayerDataObject(PlayerDataObject copy) {
        this.playerId = copy.playerId;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.email = copy.email;
        this.userId = copy.userId;
    }
}
