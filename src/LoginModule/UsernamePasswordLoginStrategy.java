package LoginModule;

import UserModule.Role;
import UserModule.User;
import UserModule.UserManager;

import java.util.Scanner;

public class UsernamePasswordLoginStrategy extends LoginStrategy {

    @Override
    public Role login(Scanner scanner) {
        System.out.println("Enter username -> ");
        String username = scanner.nextLine();
        System.out.println("Enter password -> ");
        String password = scanner.nextLine();

        UserManager userManager = UserManager.getUserManager();
        User user = userManager.getUserByName(username);

        return user != null && password.equals(user.getPassword()) ? user.getRole() : null;
    }
}
