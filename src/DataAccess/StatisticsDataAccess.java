package DataAccess;

import DataObjects.GameDataObject;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsDataAccess {

    public static List<GameDataObject> GetAllGamesByPlayerId(int playerId) {
        List<GameDataObject> allGames = GameDataAccess.GetGamesByPlayerId(playerId);
        return allGames != null ? allGames : List.of();
    }

    public static List<GameDataObject> GetCompletedGamesByPlayerId(int playerId) {
        return GetAllGamesByPlayerId(playerId).stream()
                .filter(game -> game.isGameComplete)
                .collect(Collectors.toList());
    }

    public static List<GameDataObject> GetIncompleteGamesByPlayerId(int playerId) {
        return GetAllGamesByPlayerId(playerId).stream()
                .filter(game -> !game.isGameComplete)
                .collect(Collectors.toList());
    }
}
