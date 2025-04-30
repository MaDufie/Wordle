package DomainObjects;

import DataObjects.UserDataObject;

public class UserDomainObject {
    public int userId;
    public String username;
    public String password;

    public UserDomainObject(UserDataObject userDataObject) {
        this.userId = userDataObject.userId;
        this.username = userDataObject.username;
        this.password = userDataObject.password;
    }

    public int getUserId() {
        return this.userId;
    }
}
