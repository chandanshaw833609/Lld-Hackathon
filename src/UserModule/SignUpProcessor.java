package UserModule;

import java.util.Scanner;

public class SignUpProcessor {
    public Role processSignUp() {
        UserManager userManager = UserManager.getUserManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name -> ");
        String name = scanner.nextLine();

        System.out.println("Enter your email -> ");
        String email = scanner.nextLine();

        System.out.println("Enter your password -> ");
        String password = scanner.nextLine();

        System.out.println("Normal User -> user");
        System.out.println("Become a seller -> seller");
        String input = scanner.nextLine();

        Role role = Role.USER;
        if (input.equals("seller")) role = Role.SELLER;

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRole(role);
        user.setPassword(password);

        userManager.addUser(user);

        System.out.println("Sign up successful");
        System.out.println();

        return user.getRole();
    }
}
