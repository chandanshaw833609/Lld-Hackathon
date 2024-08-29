package LoginModule;

import UserModule.Role;

import java.util.Scanner;

public abstract class LoginStrategy {
    public abstract Role login(Scanner scanner);
}
