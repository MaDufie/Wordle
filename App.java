import java.util.List;

import Controller.GameController;
import Controller.GuessController;
import Controller.PlayerController;
import DataAccess.GameDataAccess;
import DataObjects.GameDataObject;
import DomainModels.GameDomainModel;
import DomainObjects.GameDomainObject;
import DomainObjects.GuessDomainObject;
import restService.Request.CreateGameRequest;
import restService.Request.CreateGuessRequest;
import restService.Request.CreatePlayerRequest;
import restService.Response.CreateGameResponse;
import restService.Response.CreateGuessResponse;
import restService.Response.CreatePlayerResponse;

public class App {

    public static void main(String[] args) throws Exception {
        // Create a 1st player
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Dufie", "Afrane", "dufie@afrane.com", 1);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);

        // Create a new game using 1st player
        CreateGameRequest gameRequest = new CreateGameRequest(playerResponse.playerId, 2);
        CreateGameResponse gameResponse = GameController.createGame(gameRequest);

        if (!gameResponse.success) {
            System.err.println("Failed to create game: " + gameResponse.errorMessages);
            return;
        }

        // Make 6 guesses for that game
        String[] guesses = { "apple", "bears", "cider", "drift", "eagle", "frame" };

        GameDataObject updatedGame = null;

        System.out.println("\n========== STARTING GUESSES ==========");

        for (String guessedWord : guesses) {
            CreateGuessRequest guessRequest = new CreateGuessRequest(gameResponse.gameId, guessedWord);
            CreateGuessResponse guessResponse = GuessController.createGuess(guessRequest);

            // Fetch updated game after every guess (even if it failed)
            updatedGame = GameDataAccess.GetGameById(gameResponse.gameId);

            if (!guessResponse.success) {
                System.out.println("\n*** Hard Mode Violation or Guess Error ***");
                System.out.println("Guess Attempted: " + guessedWord);
                System.out.println("Error Message: " + guessResponse.errorMessage);
            } else {
                System.out.println("\nGuess Submitted: " + guessedWord);
                System.out.println("Result: " + guessResponse.result);
                System.out.println("NumGuesses after guess: " + guessResponse.numGuesses);
                System.out.println("Is Game Complete? " + guessResponse.isGameComplete);
            }

            // Check for game completion
            if (updatedGame.isGameComplete) {
                System.out.println("Game is complete after this guess.");
                break;
            }
        }

        GameDomainObject gameDomain = GameDomainModel.GetGameById(gameResponse.gameId);
        List<GuessDomainObject> guessesDomain = gameDomain.GetGuesses();

        System.out.println("\n------ All Guesses via Domain Model ------");
        for (GuessDomainObject guess : guessesDomain) {
            System.out.println("------------------------------");
            System.out.println("Ordinal: " + guess.ordinal);
            System.out.println("Guessed Word: " + guess.guessedWord);
            System.out.println("Result: " + guess.result);
            System.out.println("Game ID (from Guess): " + guess.gameId);
        }

        System.out.println("\n========== FINAL GAME STATE ==========");

        if (updatedGame != null) {
            System.out.println("Game ID: " + updatedGame.gameId);
            System.out.println("Player ID: " + updatedGame.playerId);
            System.out.println("Hidden Word: " + updatedGame.hiddenWord);
            System.out.println("Number of Guesses: " + updatedGame.numGuesses);
            System.out.println("Did Player Win: " + updatedGame.didPlayerWin);
            System.out.println("Is Game Complete: " + updatedGame.isGameComplete);
            System.out.println("Game Status: " + updatedGame.status);
        }

    }
}