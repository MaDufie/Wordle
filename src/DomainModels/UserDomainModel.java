package DomainModels;

import DataAccess.UserDataAccess;
import DataObjects.UserDataObject;
import DomainObjects.UserDomainObject;

public class UserDomainModel {

    public static boolean ValidateUserId(int userId) {
        return userId > 0 && UserDataAccess.UserExists(userId);
    }

    public static UserDomainObject CreateUser(String username, String password) {
        UserDataObject user = UserDataAccess.CreateNewUser(username, password);
        return new UserDomainObject(user);
    }

    public static UserDomainObject GetUserById(int userId) {
        UserDataObject userDataObject = UserDataAccess.GetUserById(userId);
        return new UserDomainObject(userDataObject);
    }
}
