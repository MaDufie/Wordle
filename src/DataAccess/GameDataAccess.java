package DataAccess;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import DataObjects.GameDataObject;

public class GameDataAccess {
    private static final HashMap<Integer, GameDataObject> games = new HashMap<>();
    private static int nextGameId = 1;

    private static synchronized int reserveNextGameId() {
        return nextGameId++;
    }

    public static GameDataObject CreateNewGame(int playerId, int gameTypeId, int wordLengthId, String hiddenWord) {
        int gameId = reserveNextGameId();

        GameDataObject newGame = new GameDataObject(
                gameId,
                playerId,
                gameTypeId,
                wordLengthId,
                hiddenWord,
                false,
                false,
                0,
                "Playing");

        games.put(gameId, newGame);
        return new GameDataObject(newGame);
    }

    public static GameDataObject GetGameById(int gameId) {
        GameDataObject game = games.get(gameId);
        return game != null ? new GameDataObject(game) : null;
    }

    public static List<GameDataObject> GetAllGames() {
        List<GameDataObject> allGames = new ArrayList<GameDataObject>();
        for (GameDataObject game : games.values()) {
            allGames.add(new GameDataObject(game));
        }
        return allGames;
    }

    public static List<GameDataObject> GetGamesByPlayerId(int playerId) {
        List<GameDataObject> gamesByPlayer = new ArrayList<GameDataObject>();
        for (GameDataObject game : games.values()) {
            if (game.playerId == playerId) {
                gamesByPlayer.add(new GameDataObject(game));
            }
        }
        return gamesByPlayer;
    }

}
