package test;

import Controller.GameController;
import Controller.PlayerController;
import Controller.StatisticsController;
import DataAccess.GameDataAccess;
import DataObjects.GameDataObject;
import org.junit.BeforeClass;
import org.junit.Test;
import restService.Request.CreatePlayerRequest;
import restService.Request.CreateStatisticsRequest;
import restService.Response.CreatePlayerResponse;
import restService.Response.CreateStatisticsResponse;
import restService.Request.CreateGameRequest;
import restService.Response.CreateGameResponse;


import static org.junit.Assert.*;

public class Sprint3Story7MaryStatisticsTests {

    private static int validPlayerId;

    @BeforeClass
    public static void setup() {
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Stat", "Player", "stat@user.com", 999);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);
        validPlayerId = playerResponse.getPlayerId();
    }

    @Test
    public void scenario7_1_nonexistentPlayer_shouldReturnError() {
        CreateStatisticsRequest request = new CreateStatisticsRequest(99999); // Assumed non-existent
        CreateStatisticsResponse response = StatisticsController.getStatistics(request);

        assertTrue(response.getSuccess() == false);
        assertTrue(response.getErrorMessages().contains("playerId does not exist"));
    }

    @Test
    public void scenario7_2_validPlayerWithWinLossHistory_shouldReturnCorrectStats() {
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Stats", "Tester", "statuser@wordle.com", 77);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);
        int playerId = playerResponse.getPlayerId();

        // 3 Wins: 3, 4, 3 guesses
        int[] winGuesses = { 3, 4, 3 };
        for (int guessCount : winGuesses) {
            CreateGameRequest gameRequest = new CreateGameRequest(playerId, 1); 
            CreateGameResponse gameResponse = GameController.createGame(gameRequest);

            GameDataObject game = GameDataAccess.GetGameById(gameResponse.getGameId());
            game.didPlayerWin = true;
            game.isGameComplete = true;
            game.numGuesses = guessCount;
            game.status = "Complete";
            GameDataAccess.UpdateGame(game);
        }

        // 2 Losses (always 6 guesses)
        for (int i = 0; i < 2; i++) {
            CreateGameRequest gameRequest = new CreateGameRequest(playerId, 1);
            CreateGameResponse gameResponse = GameController.createGame(gameRequest);

            GameDataObject game = GameDataAccess.GetGameById(gameResponse.getGameId());
            game.didPlayerWin = false;
            game.isGameComplete = true;
            game.numGuesses = 6;
            game.status = "Complete";
            GameDataAccess.UpdateGame(game);
        }

        CreateStatisticsRequest statsRequest = new CreateStatisticsRequest(playerId);
        CreateStatisticsResponse statsResponse = StatisticsController.getStatistics(statsRequest);

        assertTrue(statsResponse.getSuccess() == true);
        assertTrue(statsResponse.getErrorMessages() == null);
        assertTrue(statsResponse.getPlayerId() > 0);
        assertTrue(statsResponse.getTotalGames() == 5);
        assertTrue(statsResponse.getWins() == 3);
        assertTrue(statsResponse.getLosses() == 2);
        assertEquals(0.6f, statsResponse.getWinRate(), 0.01);
        assertEquals(3.33f, statsResponse.getAverageGuessesToWin(), 0.01);
    }

    @Test
    public void scenario7_3_validPlayerNoGames_shouldReturnZeroStats() {
        CreateStatisticsRequest request = new CreateStatisticsRequest(validPlayerId);
        CreateStatisticsResponse response = StatisticsController.getStatistics(request);

        assertTrue(response.getSuccess() == true);
        assertTrue(response.getErrorMessages() == null); 
        assertTrue(response.getPlayerId() > 0);       
        assertTrue(response.getTotalGames() == 0);
        assertTrue(response.getWins() == 0);
        assertTrue(response.getLosses() == 0);
        assertEquals(0.0f, response.getWinRate(), 0.001);
        assertEquals(0.0f, response.getAverageGuessesToWin(), 0.001);
    }

    @Test
    public void scenario7_4_incompleteGamesShouldNotAffectStatistics() {
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Partial", "Tester", "partial@user.com", 202);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);
        int playerId = playerResponse.getPlayerId();

        // Completed win
        CreateGameResponse winGameResponse = GameController.createGame(new CreateGameRequest(playerId, 1));
        GameDataObject winGame = GameDataAccess.GetGameById(winGameResponse.getGameId());
        winGame.didPlayerWin = true;
        winGame.isGameComplete = true;
        winGame.numGuesses = 3;
        winGame.status = "Complete";
        GameDataAccess.UpdateGame(winGame);

        // Completed loss
        CreateGameResponse lossGameResponse = GameController.createGame(new CreateGameRequest(playerId, 1));
        GameDataObject lossGame = GameDataAccess.GetGameById(lossGameResponse.getGameId());
        lossGame.didPlayerWin = false;
        lossGame.isGameComplete = true;
        lossGame.numGuesses = 6;
        lossGame.status = "Complete";
        GameDataAccess.UpdateGame(lossGame);

        // Incomplete game (should not count)
        CreateGameResponse incompleteGameResponse = GameController.createGame(new CreateGameRequest(playerId, 1));
        GameDataObject incompleteGame = GameDataAccess.GetGameById(incompleteGameResponse.getGameId());
        incompleteGame.didPlayerWin = false; // Doesn't matter
        incompleteGame.isGameComplete = false;
        incompleteGame.numGuesses = 2;
        incompleteGame.status = "Playing";
        GameDataAccess.UpdateGame(incompleteGame);

        CreateStatisticsRequest statsRequest = new CreateStatisticsRequest(playerId);
        CreateStatisticsResponse statsResponse = StatisticsController.getStatistics(statsRequest);

        assertTrue(statsResponse.getSuccess());
        assertTrue(statsResponse.getErrorMessages() == null);
        assertTrue(statsResponse.getPlayerId() > 0);
        assertTrue(statsResponse.getTotalGames() == 3);
        assertTrue(statsResponse.getWins()== 1);
        assertTrue(statsResponse.getLosses() == 1);
        assertEquals(0.5f, statsResponse.getWinRate(), 0.01); 
        assertEquals(3.0f, statsResponse.getAverageGuessesToWin(), 0.01);
    }

}
