package UserModule;

import LoginModule.EmailOtpLoginStrategy;
import LoginModule.LoginStrategy;
import LoginModule.UsernamePasswordLoginStrategy;

import java.util.Scanner;

public class LoginProcessor {
    LoginStrategy loginStrategy;
    Scanner scanner;

    public LoginProcessor() {
        scanner = new Scanner(System.in);
        this.loginStrategy = null;
    }

    public void  setLoginStrategy() {
        System.out.println("choose sign in method : ");
        System.out.println("For username and password method enter -> 1");
        System.out.println("For email and otp method enter -> 2");
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> loginStrategy = new UsernamePasswordLoginStrategy();
            case "2" -> loginStrategy = new EmailOtpLoginStrategy();
            default -> {
                System.out.println("choose correct input");
                setLoginStrategy();
            }
        }
    }

    public Role processLogin() {
        Role role = loginStrategy.login(scanner);
        if (role != null) {
            System.out.println("Login successful");
        } else {
            System.out.println("Failed to login");
        }
        System.out.println();
        return role;
    }
}
