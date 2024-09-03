package login;

import user.User;
import user.UserMetaData;

public interface LoginStrategy {
    public User processLogin(UserMetaData userMetaData);
}
