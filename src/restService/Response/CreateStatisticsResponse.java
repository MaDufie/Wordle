package restService.Response;

import DomainObjects.StatisticsDomainObject;
import java.util.List;

public class CreateStatisticsResponse {
    public boolean success;
    public List<String> errorMessages;
    public int playerId;
    public int totalGames;
    public int wins;
    public int losses;
    public float winRate; // Value between 0.0 and 1.0
    public float averageGuessesToWin;

    public CreateStatisticsResponse(StatisticsDomainObject statistics) {
        this.success = true;
        this.playerId = statistics.playerId;
        this.totalGames = statistics.totalGames;
        this.wins = statistics.wins;
        this.losses = statistics.losses;
        this.winRate = statistics.winRate;
        this.averageGuessesToWin = statistics.averageGuessesToWin;
    }

    public CreateStatisticsResponse(List<String> errorMessages) {
        this.success = false;
        this.errorMessages = errorMessages;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getTotalGames() {
        return this.totalGames;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public float getWinRate() {
        return this.winRate;
    }

    public float getAverageGuessesToWin() {
        return this.averageGuessesToWin;
    }
}
