package test;

import Controller.GameController;
import Controller.PlayerController;
import org.junit.BeforeClass;
import org.junit.Test;
import restService.Request.CreateGameRequest;
import restService.Request.CreatePlayerRequest;
import restService.Response.CreateGameResponse;
import restService.Response.CreatePlayerResponse;

import static org.junit.Assert.*;

public class Sprint1Story3MaryCreateGameTests {

    static int validPlayerId;

    @BeforeClass
    public static void setupSharedPlayer() throws Exception {
        String firstName = "Dudu";
        String lastName = "Do";
        String email = "dudu.do@example.com";
        int userId = 999;

        CreatePlayerRequest playerRequest = new CreatePlayerRequest(firstName, lastName, email, userId);
        CreatePlayerResponse player = PlayerController.createPlayer(playerRequest);
        validPlayerId = player.playerId;
    }

    @Test
    public void scenario3_1_createGame_shouldFail_whenPlayerIdIsInvalid() {
        CreateGameRequest request = new CreateGameRequest(9999, 1);
        CreateGameResponse response = GameController.createGame(request);

        assertTrue(response.getSuccess() == false);
        assertTrue(response.getErrorMessages() != null);
        assertTrue(response.getErrorMessages().contains("The playerId does not exist."));
        assertTrue(response.getGameId() == -1);

    }

    @Test
    public void scenario3_2_createGame_shouldFail_whenGameTypeIdIsInvalid() {
        CreateGameRequest request = new CreateGameRequest(validPlayerId, 9999);
        CreateGameResponse response = GameController.createGame(request);

        assertTrue(response.getSuccess() == false);
        assertTrue(response.getErrorMessages() != null);
        assertTrue(response.getErrorMessages().contains("Unsupported gameTypeId."));
        assertTrue(response.getGameId() == -1);
    }

    @Test
    public void scenario3_3_createGame_shouldAssignUniqueGameIds() {
        CreateGameRequest request1 = new CreateGameRequest(validPlayerId, 1);
        CreateGameRequest request2 = new CreateGameRequest(validPlayerId, 1);

        CreateGameResponse response1 = GameController.createGame(request1);
        CreateGameResponse response2 = GameController.createGame(request2);

        assertTrue("Game 1 Creation should be successful", response1.getSuccess() == true);
        assertTrue("Game 2 Creation should be successful", response2.getSuccess() == true);
        assertTrue("Game 1 Should have a valid game Id", response1.getGameId() > 0);
        assertTrue("Game 2 Should be a valid game Id", response2.getGameId() > 0);
        assertTrue("Game 1 & 2 should have unique game Ids", response1.getGameId() != response2.getGameId());
    }

    @Test
    public void scenario3_4_createGame_shouldSucceed_withValidInputs() {
        CreateGameRequest request = new CreateGameRequest(validPlayerId, 1);
        CreateGameResponse response = GameController.createGame(request);

        assertTrue(response.getSuccess() == true);
        assertTrue(response.getErrorMessages() == null);
        assertTrue(response.getPlayerId() == validPlayerId);
        assertTrue(response.getGameTypeId() == 1);
        assertTrue(response.getWordLength() == 5);
        assertTrue(response.getStatus() == "Playing");
        assertTrue(response.getDidPlayerWin() == false);
        assertTrue(response.getGuesses() == null);
    }

    @Test
    public void scenario3_5_createGame_shouldHaveStatusPlaying_whenSuccessful() {
        CreateGameRequest validRequest = new CreateGameRequest(validPlayerId, 1);
        CreateGameResponse validResponse = GameController.createGame(validRequest);

        assertTrue(validResponse.getStatus() != null);
        assertTrue(validResponse.getStatus() == "Playing");
    }
}
