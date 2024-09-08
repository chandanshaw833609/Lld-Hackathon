package login;

import user.User;

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
        LoginDetails loginDetails;

        // in ideal case we get loginDetails from frontend
        // so nothing to worry about this if else conditions
        // we can just call
        // loginStrategy.processLogin(loginDetails);

        if (loginStrategy instanceof EmailOtpLoginStrategy) {
            System.out.println("Enter your email ->");
            String email = scanner.nextLine();
            loginDetails = new EmailOtpLoginDetails(email);
        } else if (loginStrategy instanceof UsernamePasswordLoginStrategy){
            System.out.println("Enter your username -> ");
            String username = scanner.nextLine();
            System.out.println("Enter your password -> ");
            String password = scanner.nextLine();
            loginDetails = new UsernamePasswordLoginDetails(username, password);
        } else {
            return null;
        }

        User isAuthenticatedUser = loginStrategy.performLogin(loginDetails);

        if (isAuthenticatedUser != null) {
            System.out.println(isAuthenticatedUser.getName() + " has logged in successfully...\n");
        } else {
            System.out.println("Bad credential!!!");
            System.out.println("Failed to process login...\n");
        }
        return isAuthenticatedUser;
    }
}
