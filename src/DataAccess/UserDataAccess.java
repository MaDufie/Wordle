package DataAccess;

import DataObjects.UserDataObject;
import java.util.HashMap;

public class UserDataAccess {
    private static final HashMap<Integer, UserDataObject> users = new HashMap<>();
    private static int nextId = 1;

    private static synchronized int reserveNextUserId() {
        return nextId++;
    }

    public static UserDataObject CreateNewUser(String username, String password) {
        int userId = reserveNextUserId();
        UserDataObject user = new UserDataObject(userId, username, password);
        users.put(userId, user);
        return new UserDataObject(user);
    }

    public static UserDataObject GetUserById(int userId) {
        return users.get(userId);
    }

    public static boolean UserExists(int userId) {
        return users.containsKey(userId);
    }
}
