import BookSearch.Book;
import UserModule.Role;
import UserModule.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryManagementSystem libraryManagementSystem = LibraryManagementSystem.getInstance();

        User loggedInUser = null;
        boolean loop = true;

        while (loop) {
            
            if (loggedInUser == null) {
                System.out.println("For Signup enter -> 1");
                System.out.println("For Login enter -> 2");
            } else {
                System.out.println("For logout enter -> logout");
            }

            System.out.println("For searching book enter -> 3");

            if (loggedInUser!=null && loggedInUser.getRole()==Role.SELLER) {
                System.out.println("For adding a category enter -> 4");
                System.out.println("For adding a book enter -> 5");
            }

            System.out.println("For browsing all books enter -> 6");
            System.out.println("For exiting from application enter -> 9");
            if(loggedInUser!=null)
            {
                System.out.println("For viewing the cart  -> 7");
                System.out.println("For Purchase books  -> 8");

            }
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> loggedInUser = libraryManagementSystem.processSignUpRequest();
                case "2" -> loggedInUser = libraryManagementSystem.processLoginRequest();
                case "3" -> {
                   List<Book> books = libraryManagementSystem.processBookSearchRequest();
                   books.stream().forEach((book -> System.out.println(book.toString())));
                   if(loggedInUser!=null)
                   {
                       libraryManagementSystem.processAddToCartRequest(loggedInUser);
                   }
                }
                case "4" -> libraryManagementSystem.processAddCategoryRequest();
                case "5" -> libraryManagementSystem.processAddBookRequest();
                case "6" -> libraryManagementSystem.processBookBrowseRequest();
                case "7" -> libraryManagementSystem.processViewCartRequest(loggedInUser);
                case "8" -> libraryManagementSystem.processPaymentRequest(loggedInUser);
                case "logout" -> loggedInUser = null;
                case "9" -> loop = false;
                default -> System.out.println("Enter valid input");
            }
        }
    }
}
