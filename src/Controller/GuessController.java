package Controller;

import Common.ValidationException;
import DomainObjects.GameDomainObject;
import DomainModels.GameDomainModel;
import DomainModels.GuessDomainModel;
import DomainObjects.GuessDomainObject;
import restService.Request.CreateGuessRequest;
import restService.Response.CreateGuessResponse;

public class GuessController {

    public static CreateGuessResponse createGuess(CreateGuessRequest request) {
        try {
            GuessDomainObject guess = GuessDomainModel.submitGuess(request.getGameId(), request.getGuessedWord());
            GameDomainObject game = GameDomainModel.GetGameById(request.getGameId());

            return new CreateGuessResponse(
                    guess.getResult(),
                    game.getDidPlayerWin(),
                    game.getIsGameComplete(),
                    game.getNumGuesses());

        } catch (ValidationException vex) {
            return new CreateGuessResponse(String.join(", ", vex.getErrorMessages()));
        } catch (Exception ex) {
            return new CreateGuessResponse("Unexpected error: " + ex.getMessage());
        }
    }
}
