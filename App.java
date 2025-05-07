import Controller.GameController;
import Controller.GuessController;
import Controller.PlayerController;
import Controller.StatisticsController;
import DataAccess.GameDataAccess;
import DataObjects.GameDataObject;
import restService.Request.CreateGameRequest;
import restService.Request.CreateGuessRequest;
import restService.Request.CreatePlayerRequest;
import restService.Request.CreateStatisticsRequest;
import restService.Response.CreateGameResponse;
import restService.Response.CreateGuessResponse;
import restService.Response.CreatePlayerResponse;
import restService.Response.CreateStatisticsResponse;

public class App {

    public static void main(String[] args) throws Exception {
        // Create a 1st player
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Dufie", "Afrane", "dufie@afrane.com", 1);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);

        // Create two games for the player
        for (int i = 1; i <= 2; i++) {
            CreateGameRequest gameRequest = new CreateGameRequest(playerResponse.playerId, 2);
            CreateGameResponse gameResponse = GameController.createGame(gameRequest);

            if (!gameResponse.success) {
                System.err.println("Failed to create game: " + gameResponse.errorMessages);
                return;
            }

            // Make 6 guesses
            String[] guesses = { "apple", "bears", "cider", "drift", "eagle", "frame" };

            System.out.println("\n========== STARTING GUESSES FOR GAME " + i + " ==========");

            for (String guessedWord : guesses) {
                CreateGuessRequest guessRequest = new CreateGuessRequest(gameResponse.gameId, guessedWord);
                CreateGuessResponse guessResponse = GuessController.createGuess(guessRequest);

                GameDataObject updatedGame = GameDataAccess.GetGameById(gameResponse.gameId);

                if (!guessResponse.success) {
                    System.out.println("\n*** Guess Error ***");
                    System.out.println("Guess Attempted: " + guessedWord);
                    System.out.println("Error Message: " + guessResponse.errorMessage);
                } else {
                    System.out.println("\nGuess Submitted: " + guessedWord);
                    System.out.println("Result: " + guessResponse.result);
                    System.out.println("NumGuesses after guess: " + guessResponse.numGuesses);
                    System.out.println("Is Game Complete? " + guessResponse.isGameComplete);
                }

                if (updatedGame.isGameComplete) {
                    System.out.println("Game is complete after this guess.");
                    break;
                }
            }
        }

        CreateStatisticsRequest statsRequest = new CreateStatisticsRequest(playerResponse.playerId);
        CreateStatisticsResponse statsResponse = StatisticsController.getStatistics(statsRequest);

        System.out.println("\n========== PLAYER STATISTICS ==========");
        if (statsResponse.success) {
            System.out.println("Player ID: " + statsResponse.getPlayerId());
            System.out.println("Total Games: " + statsResponse.getTotalGames());
            System.out.println("Wins: " + statsResponse.getWins());
            System.out.println("Losses: " + statsResponse.getLosses());
            System.out.println("Win Rate: " + statsResponse.getWinRate());
            System.out.println("Average Guesses to Win: " + statsResponse.getAverageGuessesToWin());
        } else {
            System.out.println("Failed to retrieve statistics:");
            for (String error : statsResponse.getErrorMessages()) {
                System.out.println("- " + error);
            }
        }
    }
}
