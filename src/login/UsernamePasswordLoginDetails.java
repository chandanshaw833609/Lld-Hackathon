package login;

public record UsernamePasswordLoginDetails(String username, String password) implements LoginDetails {
}
