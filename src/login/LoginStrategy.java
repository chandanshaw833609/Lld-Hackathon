package login;

import user.User;

@FunctionalInterface
public interface LoginStrategy {
    public User performLogin(LoginDetails details);
}
