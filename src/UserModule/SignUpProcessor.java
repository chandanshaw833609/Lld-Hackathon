package UserModule;

import cart.Cart;
import signup.EmailSignupStrategy;
import signup.PasswordSignupStrategy;
import signup.SignupStrategy;

import java.util.Scanner;

public class SignUpProcessor {

    private SignupStrategy signupStrategy;
    private static final Scanner scanner = new Scanner(System.in);


    public User processSignUp() {
        System.out.println("Signup for Buyer ->  Enter 0");
        System.out.println("Signup for seller -> Enter 1");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Role role = Role.BUYER;
        if (input.equals("1")) role = Role.SELLER;

        String name;
        String email;
        String password;

        System.out.println("Enter your name ->");
        name = scanner.nextLine();

        UserMetaData userMetaData = new UserMetaData();
        userMetaData.setName(name);
        userMetaData.setRole(role);

        if(signupStrategy instanceof EmailSignupStrategy)
        {
            System.out.println("Enter your email ->");
            email = scanner.nextLine();
            userMetaData.setEmail(email);
        }
        else if(signupStrategy instanceof PasswordSignupStrategy)
        {
            System.out.println("Enter your password -> ");
            password = scanner.nextLine();
            userMetaData.setPassword(password);
        }

        UserManager userManager = UserManager.getUserManagerInstance();
        User user= constructUserFromMetaData(userMetaData);
        userManager.addUser(user);

        System.out.println("Sign up successful");
        System.out.println();
        return user;
    }

    private User constructUserFromMetaData(UserMetaData userMetaData)
    {
        User user = new User();
        user.setPassword(userMetaData.getPassword());
        user.setEmail(userMetaData.getEmail());
        user.setName(userMetaData.getName());
        user.setRole(userMetaData.getRole());
        return user;
    }

    public void setSignupStrategy() {
        System.out.println("Do you want to signup via email / password ?-> ");
        System.out.println("Enter 1 to signup via email ->");
        System.out.println("Enter 2 to signup via password ->");
        String modeOfSignUp = scanner.nextLine();
        switch (modeOfSignUp)
        {
            case "1" -> signupStrategy = new EmailSignupStrategy();
            case "2" -> signupStrategy = new PasswordSignupStrategy();
        }
    }
}
