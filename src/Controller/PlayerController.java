package Controller;

import java.util.List;

import Common.ValidationException;
import DomainModels.PlayerDomainModel;
import DomainObjects.PlayerDomainObject;
import restService.Request.CreatePlayerRequest;
import restService.Response.CreatePlayerResponse;

public class PlayerController {

    public static CreatePlayerResponse createPlayer(CreatePlayerRequest request) {
        try {
            PlayerDomainObject player = PlayerDomainModel.createPlayer(request.getFirstName(), request.getLastName(),
                    request.getEmail(), request.getUserId());
            return new CreatePlayerResponse(player);
        } catch (ValidationException vex) {
            return new CreatePlayerResponse(vex.getErrorMessages());
        } catch (Exception ex) {
            return new CreatePlayerResponse(List.of("An error occurred while creating player: " + ex.getMessage()));
        }
    }

}
