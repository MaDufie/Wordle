package DataObjects;

public class UserDataObject {
    public int userId;
    public String username;
    public String password;

    public UserDataObject(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserDataObject(UserDataObject copy) {
        this.userId = copy.userId;
        this.username = copy.username;
        this.password = copy.password;
    }
}
