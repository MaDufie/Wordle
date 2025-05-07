package DomainObjects;

import DataObjects.StatisticsDataObject;

public class StatisticsDomainObject {
    public int playerId;
    public int totalGames;
    public int wins;
    public int losses;
    public float winRate;
    public float averageGuessesToWin;

    public StatisticsDomainObject(StatisticsDataObject statisticsDataObject) {
        this.playerId = statisticsDataObject.playerId;
        this.totalGames = statisticsDataObject.totalGames;
        this.wins = statisticsDataObject.wins;
        this.losses = statisticsDataObject.losses;
        this.winRate = statisticsDataObject.winRate;
        this.averageGuessesToWin = statisticsDataObject.averageGuessesToWin;
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
