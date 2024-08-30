package UserModule;

import login.EmailLoginStrategy;
import login.LoginStrategy;
import login.PasswordLoginStrategy;

import java.util.Scanner;

public class LoginProcessor {
    LoginStrategy loginStrategy;
    Scanner scanner;

    public LoginProcessor() {
        scanner = new Scanner(System.in);
        this.loginStrategy = null;
    }

    public void setLoginStrategy() {
        System.out.println("choose login in method : ");
        System.out.println("For username and password way of login enter -> 1");
        System.out.println("For email way of login enter -> 2");
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> loginStrategy = new PasswordLoginStrategy();
            case "2" -> loginStrategy = new EmailLoginStrategy();
        }
    }

    public User processLogin() {
        System.out.println("Enter your name ->");
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
            userMetaData.setPassword(password);
        }

        User isAuthenticatedUser = loginStrategy.processLogin(userMetaData);

        if (isAuthenticatedUser != null) {
            System.out.println("Login successful");
        } else {
            System.out.println("Failed to processSignup");
        }
        System.out.println();
        return isAuthenticatedUser;
    }
}
