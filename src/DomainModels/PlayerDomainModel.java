package DomainModels;

import Common.ValidationException;
import DataAccess.PlayerDataAccess;
import DataObjects.PlayerDataObject;
import DomainObjects.PlayerDomainObject;
import java.util.ArrayList;
import java.util.List;

public class PlayerDomainModel {

    public static boolean ValidatePlayerId(int playerId) {
        return playerId > 0 && PlayerDataAccess.PlayerExists(playerId);
    }

    public static PlayerDomainObject createPlayer(String firstName, String lastName, String email, int userId)
            throws ValidationException {
        List<String> errors = new ArrayList<>();

        if (!isValidName(firstName)) {
            errors.add("First name is required.");
        }

        if (!isValidName(lastName)) {
            errors.add("Last name is required.");
        }

        if (!isValidEmail(email)) {
            errors.add("Email is invalid.");
        }

        if (userId <= 0) {
            errors.add("User ID must be a positive integer.");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        PlayerDataObject player = PlayerDataAccess.CreateNewPlayer(
                firstName,
                lastName,
                email,
                userId);

        return new PlayerDomainObject(player);
    }

    public static PlayerDomainObject GetPlayerById(int playerId) {
        PlayerDataObject playerDataObject = PlayerDataAccess.GetPlayerById(playerId);
        return new PlayerDomainObject(playerDataObject);
    }

    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
