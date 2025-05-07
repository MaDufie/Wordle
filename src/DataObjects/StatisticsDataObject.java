package DataObjects;

public class StatisticsDataObject {
    public int playerId;
    public int totalGames;
    public int wins;
    public int losses;
    public float winRate;
    public float averageGuessesToWin;

    public StatisticsDataObject(int playerId, int totalGames, int wins, int losses, float winRate,
            float averageGuessesToWin) {
        this.playerId = playerId;
        this.totalGames = totalGames;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.averageGuessesToWin = averageGuessesToWin;
    }

    public StatisticsDataObject(StatisticsDataObject copy) {
        this.playerId = copy.playerId;
        this.totalGames = copy.totalGames;
        this.wins = copy.wins;
        this.losses = copy.losses;
        this.winRate = copy.winRate;
        this.averageGuessesToWin = copy.averageGuessesToWin;
    }
}
