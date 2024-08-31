package login;

import user.User;
import user.UserMetaData;

import java.util.Scanner;

public class LoginProcessor {
    LoginStrategy loginStrategy;


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
            case "1" -> loginStrategy = new PasswordLoginStrategy();
            case "2" -> loginStrategy = new EmailLoginStrategy();
            default -> {
                System.out.println("Choose a valid option...\n");
                setLoginStrategy();
            }
        }
    }

    public User processLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name ->");
        String name = scanner.nextLine();
        UserMetaData userMetaData = new UserMetaData();
        userMetaData.setName(name);

        if (loginStrategy instanceof EmailLoginStrategy) {
            System.out.println("Enter your email ->");
            String email = scanner.nextLine();
            userMetaData.setEmail(email);
        } else {
            System.out.println("Enter your password -> ");
            String password = scanner.nextLine();
            userMetaData.setName(name);
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
