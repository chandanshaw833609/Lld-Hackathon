package user;

import book.BookCategory;
import book_search.BookCategoryManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminRequestProcessor {
    private final UserManager userManager = UserManager.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private final BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();

    public void displayAdminMenu(User admin) {
        System.out.println("For adding a seller enter -> 1");
        System.out.println("For removing a seller enter -> 2");
        System.out.println("For logout enter logout -> logout");
        String option = scanner.nextLine();
        switch (option) {
            case "1" -> addSeller();
            case "2" -> removeSeller();
            case "logout" -> {
                admin = null;
                System.out.println("Logged out successfully...\n");
            }
            default -> System.out.println("choose a valid option");
        }
    }

    public void addSeller() {
        System.out.println("Enter name -> ");
        String name = scanner.nextLine();
        System.out.println("Enter username -> ");
        String username = scanner.nextLine();
        System.out.println("Enter email -> ");
        String email = scanner.nextLine();
        System.out.println("Enter password -> ");
        String password = scanner.nextLine();

        User user = new Seller();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        userManager.saveUser(user);
    }
    public void removeSeller() {
        List<User> sellerList = userManager
                .getAllUser()
                .stream()
                .filter(user -> user.getRole() == Role.SELLER)
                .toList();
        sellerList.forEach(seller -> System.out.println(seller.toString()));

        System.out.println("Enter seller name to remove that seller -> ");
        String sellerName  = scanner.nextLine();

        Optional<User> optionalSeller =  sellerList
                .stream()
                .filter(seller -> !seller.getName().equals(sellerName))
                .findFirst();
        if (optionalSeller.isPresent()) {
            userManager.removeUser(optionalSeller.get().getId());
        } else {
            System.out.println(sellerName + " is not registered as a seller!!!\n");
        }
    }

    public void addBookCategory() {
        System.out.println("Enter category name -> ");
        String categoryName = scanner.nextLine();
        BookCategory bookCategory = new BookCategory(categoryName);
        bookCategoryManager.addBookCategory(bookCategory);
    }
}
