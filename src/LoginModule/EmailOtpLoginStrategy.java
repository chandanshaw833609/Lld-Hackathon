package LoginModule;

import UserModule.Role;
import UserModule.User;
import UserModule.UserManager;

import java.util.Scanner;

public class EmailOtpLoginStrategy extends LoginStrategy {

    @Override
    public Role login(Scanner scanner) {
        UserManager userManager = UserManager.getUserManager();
        System.out.println("Enter your email : ");
        String email = scanner.nextLine();
        User user = userManager.getUserByEmail(email);

        return user != null ? user.getRole() : null;
    }
}
