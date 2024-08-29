import BookSearch.BookBrowseProcessor;
import BookSearch.BookCategoryMgr;
import BookSearch.BookMgr;
import BookSearch.SearchBookProcessor;
import UserModule.LoginProcessor;
import UserModule.Role;
import UserModule.SignUpProcessor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookCategoryMgr bookCategoryMgr = BookCategoryMgr.getBookCategoryMgr();
        BookMgr bookMgr = BookMgr.getBookMgr();
        SearchBookProcessor searchBookProcessor = SearchBookProcessor.getSearchBookProcessor();
        LoginProcessor loginProcessor = new LoginProcessor();
        SignUpProcessor signUpProcessor = new SignUpProcessor();
        BookBrowseProcessor bookBrowseProcessor = new BookBrowseProcessor();

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
                case "1" -> {
                    loginProcessor.setLoginStrategy();
                    loggedInRole = loginProcessor.processLogin();
                }
                case "2" -> loggedInRole = signUpProcessor.processSignUp();
                case "3" -> searchBookProcessor.processSearchBook();
                case "4" -> bookCategoryMgr.addBookCategory();
                case "5" -> bookMgr.addBook();
                case "6" -> bookBrowseProcessor.browseBooks();
                case "logout" -> loggedInRole = null;
                case "9" -> loop = false;
                default -> System.out.println("Enter valid input");
            }
        }
    }
}
