package test;

import Controller.GameController;
import Controller.GuessController;
import Controller.PlayerController;
import DataAccess.GameDataAccess;
import DataObjects.GameDataObject;

import org.junit.BeforeClass;
import org.junit.Test;
import restService.Request.CreateGameRequest;
import restService.Request.CreateGuessRequest;
import restService.Request.CreatePlayerRequest;
import restService.Response.CreateGameResponse;
import restService.Response.CreateGuessResponse;
import restService.Response.CreatePlayerResponse;

import static org.junit.Assert.*;

public class Sprint2Story4MaryHardModeTests {

    private static int validPlayerId;

    @BeforeClass
    public static void setup() {
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Test", "User", "hard@mode.com", 101);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);
        validPlayerId = playerResponse.playerId;
    }

    @Test
    public void scenario4_1_invalidHardModeGuess_shouldFail() {
        // Create game in hard mode
        CreateGameRequest gameRequest = new CreateGameRequest(validPlayerId, 2);
        CreateGameResponse gameResponse = GameController.createGame(gameRequest);
        int gameId = gameResponse.getGameId();

        // Set consistent hidden word
        GameDataObject game = GameDataAccess.GetGameById(gameId);
        game.hiddenWord = "cheek";
        GameDataAccess.UpdateGame(game);

        // First guess: crate → G*G** (c and e are correct in position)
        CreateGuessRequest guess1 = new CreateGuessRequest(gameId, "crest");
        CreateGuessResponse response1 = GuessController.createGuess(guess1);
        assertTrue(response1.getSuccess() == true);

        // Second guess violates green lock: "brine" does not preserve 'c' and 'e'
        CreateGuessRequest guess2 = new CreateGuessRequest(gameId, "brine");
        CreateGuessResponse response2 = GuessController.createGuess(guess2);
        assertTrue("Expected guess to fail due to Hard Mode violation", response2.getSuccess() == false);
        assertTrue(response2.getErrorMessage() != null);
        assertTrue("Should mention Hard Mode rule", response2.errorMessage
                .contains("Hard Mode: All correct letters must stay in the same position.") == true);
    }

    @Test
    public void scenario4_2_createHardModeGame_shouldSucceed() {
        CreateGameRequest request = new CreateGameRequest(validPlayerId, 2);
        CreateGameResponse response = GameController.createGame(request);

        assertTrue(response.getSuccess() == true);
        assertTrue(response.getErrorMessages() == null);
        assertTrue(response.getGameId() > 0);
        assertTrue(response.getGameTypeId() == 2);
        assertTrue(response.getPlayerId() == validPlayerId);
        assertTrue(response.getStatus() == "Playing");
        assertTrue(response.getWordLength() == 5);
        assertTrue(response.getDidPlayerWin() == false);
        assertTrue(response.getGuesses() == null);
    }

    @Test
    public void scenario4_3_validHardModeGuess_shouldSucceed() {
        CreateGameRequest gameRequest = new CreateGameRequest(validPlayerId, 2);
        CreateGameResponse gameResponse = GameController.createGame(gameRequest);
        int gameId = gameResponse.getGameId();

        GameDataObject game = GameDataAccess.GetGameById(gameId);
        game.hiddenWord = "scape";
        GameDataAccess.UpdateGame(game);

        // First guess: scene → GG**G (s, c, e are correct in position)
        CreateGuessRequest guess1 = new CreateGuessRequest(gameId, "scene");
        CreateGuessResponse response1 = GuessController.createGuess(guess1);
        assertTrue(response1.getSuccess() == true);

        // Valid second guess: scale → preserves 's', 'c', 'a' and 'e' in their correct positions
        CreateGuessRequest g2 = new CreateGuessRequest(gameId, "scale");
        CreateGuessResponse res2 = GuessController.createGuess(g2);
        assertTrue("Second guess should succeed", res2.getSuccess() == true);
        assertTrue(res2.getErrorMessage() == null);
        assertTrue(res2.getResult().equals("GGG*G"));
        assertTrue(res2.getIsGameComplete() == false);
        assertTrue(res2.getDidPlayerWin() == false);
        assertTrue(res2.getNumGuesses() == 2);
    }

    @Test
    public void Scenario4_4_hardMode_ignoresYellowLettersForLocking() {
        CreateGameResponse gameResponse = GameController.createGame(new CreateGameRequest(validPlayerId, 2));
        int gameId = gameResponse.getGameId();

        GameDataObject game = GameDataAccess.GetGameById(gameId);
        game.hiddenWord = "chair";
        GameDataAccess.UpdateGame(game);

        CreateGuessResponse firstGuess = GuessController.createGuess(new CreateGuessRequest(gameId, "reach"));
        assertTrue(firstGuess.getSuccess() == true);

        CreateGuessResponse secondGuess = GuessController.createGuess(new CreateGuessRequest(gameId, "crane"));
        assertTrue("Should succeed — yellow r is not locked", secondGuess.getSuccess() == true);
        assertTrue(secondGuess.getResult().equals("GYG**"));
        assertTrue(secondGuess.getIsGameComplete() == false);
        assertTrue(secondGuess.getDidPlayerWin() == false);
        assertTrue(secondGuess.getNumGuesses() == 2);
    }
}
