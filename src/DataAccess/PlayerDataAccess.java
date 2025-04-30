package DataAccess;

import java.util.HashMap;
import DataObjects.PlayerDataObject;

public class PlayerDataAccess {
    private static final HashMap<Integer, PlayerDataObject> players = new HashMap<>();
    private static int nextId = 1;

    private static synchronized int reserveNextPlayerId() {
        return nextId++;
    }

    public static PlayerDataObject GetPlayerById(int playerId) {
        PlayerDataObject player = players.get(playerId);
        return player != null ? new PlayerDataObject(player) : null;
    }

    public static PlayerDataObject CreateNewPlayer(String firstName, String lastName, String email, int userId) {
        int playerId = reserveNextPlayerId();
        PlayerDataObject player = new PlayerDataObject(playerId, firstName, lastName, email, userId);
        players.put(playerId, player);
        return new PlayerDataObject(player);
    }

    public static boolean PlayerExists(int playerId) {
        return players.containsKey(playerId);
    }
}
