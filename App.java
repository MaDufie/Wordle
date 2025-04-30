import Controller.GameController;
import Controller.PlayerController;
import restService.Request.CreateGameRequest;
import restService.Request.CreatePlayerRequest;
import restService.Response.CreateGameResponse;
import restService.Response.CreatePlayerResponse;

public class App {

    public static void main(String[] args) throws Exception {
        // Create a 1st player
        CreatePlayerRequest playerRequest = new CreatePlayerRequest("Dufie", "Afrane", "dufie@afrane.com", 1);
        CreatePlayerResponse playerResponse = PlayerController.createPlayer(playerRequest);

        // Create a new game using 1st player
        CreateGameRequest gameRequest = new CreateGameRequest(playerResponse.playerId, 1);
        CreateGameResponse gameResponse = GameController.createGame(gameRequest);

        // Create a 2nd player
        CreatePlayerRequest playerRequest2 = new CreatePlayerRequest("Sarah", "Afrane", "sarah@afrane.com", 2);
        CreatePlayerResponse playerResponse2 = PlayerController.createPlayer(playerRequest2);

        // Create a new game using 2nd player
        CreateGameRequest gameRequest2 = new CreateGameRequest(playerResponse2.playerId, 1);
        CreateGameResponse gameResponse2 = GameController.createGame(gameRequest2);

    }
}
