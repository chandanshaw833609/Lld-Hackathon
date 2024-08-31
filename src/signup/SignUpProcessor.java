package signup;

import UserModule.Buyer;
import UserModule.User;
import UserModule.UserManager;
import signup.EmailSignupStrategy;
import signup.PasswordSignupStrategy;
import signup.SignupStrategy;

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

        if(signupStrategy instanceof EmailSignupStrategy)
        {
            System.out.println("Enter your email ->");
            String email = scanner.nextLine();
            user.setEmail(email);

        }
        if(signupStrategy instanceof PasswordSignupStrategy)
        {
            System.out.println("Enter your password -> ");
            String password = scanner.nextLine();
            user.setPassword(password);
        }


        userManager.saveUser(user);

        System.out.println(user.getName() + ", your sign up is successful...\n");
    }

    public void setSignupStrategy() {
        System.out.println("Enter 1 to signup via email ->");
        System.out.println("Enter 2 to signup via password ->");
        String modeOfSignUp = scanner.nextLine();
        switch (modeOfSignUp)
        {
            case "1" -> signupStrategy = new EmailSignupStrategy();
            case "2" -> signupStrategy = new PasswordSignupStrategy();
            default -> {
                System.out.println("choose a valid option!!!\n");
                setSignupStrategy();
            }
        }
    }
}
