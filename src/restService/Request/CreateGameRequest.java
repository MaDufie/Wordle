package restService.Request;

public class CreateGameRequest {
    public int playerId;
    public int gameTypeId;

    public CreateGameRequest(int playerid, int gametypeid) {
        this.playerId = playerid;
        this.gameTypeId = gametypeid;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }
}
