package DomainModels;

import Common.ValidationException;
import DataAccess.PlayerDataAccess;
import DataAccess.StatisticsDataAccess;
import DataObjects.GameDataObject;
import DataObjects.PlayerDataObject;
import DataObjects.StatisticsDataObject;
import DomainObjects.StatisticsDomainObject;
import java.util.List;

public class StatisticsDomainModel {

    public static StatisticsDomainObject getStatistics(int playerId) throws Exception {

        PlayerDataObject player = PlayerDataAccess.GetPlayerById(playerId);
        if (player == null) {
            throw new ValidationException(List.of("playerId does not exist"));
        }

        List<GameDataObject> allGames = StatisticsDataAccess.GetAllGamesByPlayerId(playerId);
        List<GameDataObject> completedGames = StatisticsDataAccess.GetCompletedGamesByPlayerId(playerId);

        int totalGames = allGames.size(); // Includes incomplete
        int completeGames = completedGames.size();

        int wins = 0;
        int losses = 0;
        int totalGuessesToWin = 0;

        for (GameDataObject game : completedGames) {
            if (game.didPlayerWin) {
                wins++;
                totalGuessesToWin += game.numGuesses;
            } else {
                losses++;
            }
        }

        float winRate = completeGames > 0 ? (float) wins / completeGames : 0.0f;
        float averageGuessesToWin = wins > 0 ? (float) totalGuessesToWin / wins : 0.0f;

        StatisticsDataObject statistics = new StatisticsDataObject(playerId,
                totalGames,
                wins,
                losses,
                winRate,
                averageGuessesToWin);

        return new StatisticsDomainObject(statistics);
    }
}
