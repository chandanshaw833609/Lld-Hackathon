package request_processor;

import book_category.BookCategory;
import book_category.BookCategoryManager;
import user.Role;
import seller.Seller;
import user.User;
import user.UserManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminRequestProcessor {
    private final UserManager userManager = UserManager.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private final BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();


    public void processAddSellerRequest() {
        System.out.println("Enter name -> ");
        String name = scanner.nextLine();
        System.out.println("Enter username -> ");
        String username = scanner.nextLine();

        User registeredUser = userManager.getUserByUsername(username);
        if (registeredUser != null) {
            System.out.println("This username is already exist...\n");
            return;
        }
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
        System.out.println("Seller has been added successfully!!!\n");
    }
    public void processRemoveSellerRequest() {
        List<User> sellerList = userManager
                .getAllUser()
                .stream()
                .filter(user -> user.getRole() == Role.SELLER)
                .toList();
        if (sellerList.isEmpty()) {
            System.out.println("Currently no seller has been registered...\n");
            return;
        }
        System.out.println("List of all the sellers -> ");
        sellerList.forEach(seller -> System.out.println("-> {Name= '"+ seller.getName() +"', username= '" + seller.getUsername() + "'}"));

        System.out.println("\nEnter seller username to remove that seller -> ");
        String sellerUsername  = scanner.nextLine();

        Optional<User> optionalSeller =  sellerList
                .stream()
                .filter(seller -> seller.getUsername().equals(sellerUsername))
                .findFirst();

        if (optionalSeller.isPresent()) {
            userManager.removeUser(optionalSeller.get().getId());
            System.out.println(sellerUsername + " has been removed successfully!!!\n");
        } else {
            System.out.println(sellerUsername + " is not registered as a seller!!!\n");
        }
    }

    public void processAddBookCategoryRequest() {
        System.out.println("Enter category name -> ");
        String categoryName = scanner.nextLine();
        BookCategory category = bookCategoryManager.getCategoryByName(categoryName);
        if (category != null) {
            System.out.println("Enter a different category name...");
            System.out.println("This book category already exist!!!\n");
            return;
        }
        BookCategory bookCategory = new BookCategory(categoryName);
        bookCategoryManager.addBookCategory(bookCategory);
        System.out.println("Category added successfully...\n");
    }
}
