import BookSearch.BookBrowseProcessor;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;
import BookSearch.BookSearchProcessor;
import UserModule.LoginProcessor;
import UserModule.Role;
import UserModule.SignUpProcessor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryManagementSystem libraryManagementSystem = LibraryManagementSystem.getInstance();

        Role loggedInRole = null;
        boolean loop = true;

        while (loop) {
            
            if (loggedInRole == null) {
                System.out.println("For login enter -> 1");
                System.out.println("For signup enter -> 2");
            } else {
                System.out.println("For logout enter -> logout");
            }

            System.out.println("For searching book enter -> 3");

            if (loggedInRole == Role.SELLER) {
                System.out.println("For adding a category enter -> 4");
                System.out.println("For adding a book enter -> 5");
            }

            System.out.println("For browsing all books enter -> 6");
            System.out.println("For exiting from application enter -> 9");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> loggedInRole = libraryManagementSystem.processLoginRequest();
                case "2" -> loggedInRole = libraryManagementSystem.processSignUpRequest();
                case "3" -> libraryManagementSystem.processBookSearchRequest();
                case "4" -> libraryManagementSystem.processAddCategoryRequest();
                case "5" -> libraryManagementSystem.processAddBookRequest();
                case "6" -> libraryManagementSystem.processBookBrowseRequest();
                case "logout" -> loggedInRole = null;
                case "9" -> loop = false;
                default -> System.out.println("Enter valid input");
            }
        }
    }
}
