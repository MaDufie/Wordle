package Controller;

import java.util.ArrayList;

import Common.ValidationException;
import DomainModels.GameDomainModel;
import DomainObjects.GameDomainObject;
import restService.Request.CreateGameRequest;
import restService.Response.CreateGameResponse;

public class GameController {

    public static CreateGameResponse createGame(CreateGameRequest request) {
        try {
            GameDomainObject game = GameDomainModel.createNewGame(request.getPlayerId(), request.getGameTypeId());
            return new CreateGameResponse(game);
        } catch (ValidationException vex) {
            return new CreateGameResponse(vex.getErrorMessages());
        } catch (Exception ex) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(ex.getMessage());
            return new CreateGameResponse(errors);
        }
    }
}
