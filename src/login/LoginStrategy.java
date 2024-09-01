package login;

import UserModule.User;
import UserModule.UserMetaData;

public interface LoginStrategy {
    public User processLogin(UserMetaData userMetaData);
}
