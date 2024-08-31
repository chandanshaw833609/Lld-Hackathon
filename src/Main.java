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

            System.out.println("For browsing all books enter -> 3");
            System.out.println("For searching book enter -> 4");

            if (loggedInUser!=null && loggedInUser.getRole()==Role.SELLER) {
                System.out.println("For adding a category enter -> 5");
                System.out.println("For adding a book enter -> 6");
            }


            if (loggedInUser!=null && loggedInUser.getRole() == Role.BUYER) {
                System.out.println("For viewing the cart  -> 7");
                System.out.println("For viewing purchase history enter -> 8");
                System.out.println("For Purchase books  -> 9");
            }

            System.out.println("For exiting from application enter -> exit");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    if (loggedInUser != null) {
                        loggedInUser = libraryManagementSystem.processSignUpRequest();
                    }
                }
                case "2" -> {
                    if (loggedInUser == null) {
                        loggedInUser = libraryManagementSystem.processLoginRequest();
                    }
                }
                case "3" -> libraryManagementSystem.processBookBrowseRequest();
                case "4" -> {
                   List<Book> books = libraryManagementSystem.processBookSearchRequest();
                   books.forEach((book -> System.out.println(book.toString())));
                   if (loggedInUser!=null && loggedInUser.getRole() == Role.BUYER) {
                       libraryManagementSystem.processAddToCartRequest(loggedInUser);
                   }
                }
                case "5" -> {
                    if (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                        libraryManagementSystem.processAddCategoryRequest();
                    }
                }
                case "6" -> {
                    if (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                        libraryManagementSystem.processAddBookRequest();
                    }
                }
                case "7" -> {
                    if (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                        libraryManagementSystem.processViewCartRequest(loggedInUser);
                    }
                }
                case "8" -> {
                    if (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                        libraryManagementSystem.processViewPurchaseHistoryRequest(loggedInUser);
                    }
                }
                case "9" -> {
                    if (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                        libraryManagementSystem.processPaymentRequest(loggedInUser);
                    }
                }

                case "logout" -> {
                    loggedInUser = null;
                    System.out.println("Logout successfully...\n");
                }
                case "exit" -> loop = false;
                default -> System.out.println("Enter valid input!!!\n");
            }
        }
    }
}
