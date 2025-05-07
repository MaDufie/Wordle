package restService.Request;

public class CreateStatisticsRequest {
    private int playerId;

    public CreateStatisticsRequest(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return this.playerId;
    }
}
