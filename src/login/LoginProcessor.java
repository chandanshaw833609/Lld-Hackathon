package login;

import UserModule.User;
import UserModule.UserMetaData;

import java.util.Scanner;

public class LoginProcessor {
    private LoginStrategy loginStrategy;

    public LoginProcessor() {
        this.loginStrategy = null;
    }

    public void setLoginStrategy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("choose login in method : ");
        System.out.println("For username and password way of login enter -> 1");
        System.out.println("For email way of login enter -> 2");
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> loginStrategy = new UsernamePasswordLoginStrategy();
            case "2" -> loginStrategy = new EmailOtpLoginStrategy();
            default -> {
                System.out.println("Choose a valid option...\n");
                setLoginStrategy();
            }
        }
    }

    public User processLogin() {
        Scanner scanner = new Scanner(System.in);
        UserMetaData userMetaData = new UserMetaData();

        if (loginStrategy instanceof EmailOtpLoginStrategy) {
            System.out.println("Enter your email ->");
            String email = scanner.nextLine();
            userMetaData.setEmail(email);
        } else {
            System.out.println("Enter your username -> ");
            String username = scanner.nextLine();
            userMetaData.setUsername(username);
            System.out.println("Enter your password -> ");
            String password = scanner.nextLine();
            userMetaData.setPassword(password);
        }

        User isAuthenticatedUser = loginStrategy.processLogin(userMetaData);

        if (isAuthenticatedUser != null) {
            System.out.println(isAuthenticatedUser.getName() + " has logged in successfully...\n");
        } else {
            System.out.println("Bad credential!!!");
            System.out.println("Failed to process login...\n");
        }
        return isAuthenticatedUser;
    }
}
