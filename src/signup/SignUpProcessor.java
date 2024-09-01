package signup;

<<<<<<< HEAD
import UserModule.Buyer;
import UserModule.User;
import UserModule.UserManager;
=======
import user.Buyer;
import user.User;
import user.UserManager;
>>>>>>> main

import java.util.Scanner;

public class SignUpProcessor {

    private SignupStrategy signupStrategy;
    private static final Scanner scanner = new Scanner(System.in);
    private final UserManager userManager = UserManager.getInstance();

    public void processSignUp() {

        User user = new Buyer();

        System.out.println("Enter name ->");
        String name = scanner.nextLine();
        user.setName(name);

        if(signupStrategy instanceof EmailOtpSignupStrategy)
        {
            System.out.println("Enter your email ->");
            String email = scanner.nextLine();
            user.setEmail(email);

            // checking email already exist or not
            User registeredUser = userManager.getUserByEmail(email);
            if (registeredUser != null) {
                System.out.println("Try signing up with different email...");
                System.out.println("This email is already registered!!!\n");
                processSignUp();
                return;
            }

        }
        if(signupStrategy instanceof UsernamePasswordSignupStrategy)
        {
            System.out.println("Enter your username -> ");
            String username = scanner.nextLine();
            user.setUsername(username);

            // checking username already exist or not
            User registeredUser = userManager.getUserByUsername(username);
            if (registeredUser != null) {
                System.out.println("Try signing up with different username...");
                System.out.println("This username is already registered!!!\n");
                processSignUp();
                return;
            }

            System.out.println("Enter your password -> ");
            String password = scanner.nextLine();
            user.setPassword(password);
        }

        // finally saving the user in the database...
        userManager.saveUser(user);

        System.out.println(user.getName() + ", your sign up is successful...\n");
    }

    public void setSignupStrategy() {
        System.out.println("Enter 1 to signup via email ->");
        System.out.println("Enter 2 to signup via password ->");
        String modeOfSignUp = scanner.nextLine();
        switch (modeOfSignUp)
        {
            case "1" -> signupStrategy = new EmailOtpSignupStrategy();
            case "2" -> signupStrategy = new UsernamePasswordSignupStrategy();
            default -> {
                System.out.println("choose a valid option!!!\n");
                setSignupStrategy();
            }
        }
    }
}
