import user.*;
import login.LoginProcessor;
import signup.SignUpProcessor;

import java.util.Scanner;

public class LibraryManagementSystem {
    private final AdminRequestProcessor adminRequestProcessor = new AdminRequestProcessor();
    private final SellerRequestProcessor sellerRequestProcessor = new SellerRequestProcessor();
    private final BuyerRequestProcessor buyerRequestProcessor = new BuyerRequestProcessor();
    private final LoginProcessor loginProcessor = new LoginProcessor();
    private final SignUpProcessor signUpProcessor = new SignUpProcessor();
    private User loggedInUser;
    private boolean loop = true;
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            while (loggedInUser != null && loggedInUser.getRole() == Role.ADMIN) {
                System.out.println("For adding a seller enter -> 1");
                System.out.println("For removing a seller enter -> 2");
                System.out.println("For adding book category enter -> 3");
                System.out.println("For logout enter -> logout");
                String option = scanner.nextLine();
                switch (option) {
                    case "1" -> adminRequestProcessor.addSeller();
                    case "2" -> adminRequestProcessor.removeSeller();
                    case "3" -> adminRequestProcessor.addBookCategory();
                    case "logout" -> {
                        loggedInUser = null;
                        System.out.println("Logged out successfully...\n");
                    }
                    default -> System.out.println("choose a valid option\n");
                }
            }
            while (loggedInUser != null && loggedInUser.getRole() == Role.SELLER) {
                System.out.println("For adding a book enter -> 1");
                System.out.println("For viewing sales enter -> 2");
                System.out.println("For viewing inventory enter -> 3");
                System.out.println("For logout enter logout -> logout");
                String option = scanner.nextLine();
                switch (option) {
                    case "1" -> sellerRequestProcessor.processAddBookRequest((Seller) loggedInUser);
                    case "2" -> sellerRequestProcessor.processViewSalesHistoryRequest((Seller) loggedInUser);
                    case "3" -> sellerRequestProcessor.processViewInventoryRequest((Seller) loggedInUser);
                    case "logout" -> {
                        loggedInUser = null;
                        System.out.println("Logged out successfully...\n");
                    }
                    default -> System.out.println("choose a valid option\n");
                }
            }
            while (loggedInUser != null && loggedInUser.getRole() == Role.BUYER) {
                System.out.println("For browsing book enter -> 0");
                System.out.println("For searching book enter -> 1");
                System.out.println("For viewing cart enter -> 2");
                System.out.println("For making a purchase enter -> 3");
                System.out.println("For viewing purchase history enter -> 4");
                System.out.println("For logout enter -> logout");
                String option = scanner.nextLine();
                switch (option) {
                    case "0" -> buyerRequestProcessor.processBookBrowseRequest((Buyer) loggedInUser);
                    case "1" -> buyerRequestProcessor.processBookSearchRequest((Buyer) loggedInUser);
                    case "2" -> buyerRequestProcessor.processViewCartRequest((Buyer) loggedInUser);
                    case "3" -> buyerRequestProcessor.processPaymentRequest((Buyer) loggedInUser);
                    case "4" -> buyerRequestProcessor.processViewPurchaseHistoryRequest((Buyer) loggedInUser);
                    case "logout" -> {
                        loggedInUser = null;
                        System.out.println("Logged out successfully...\n");
                    }
                    default -> System.out.println("choose a valid option\n");
                }
            }
            while (loggedInUser == null && loop) {
                System.out.println("For login enter -> 1");
                System.out.println("For signup enter -> 2");
                System.out.println("To exit enter -> exit");
                String input = scanner.nextLine();
                switch (input) {
                    case "1" -> {
                        loginProcessor.setLoginStrategy();
                        loggedInUser = loginProcessor.processLogin();
                    }
                    case "2" -> {
                        signUpProcessor.setSignupStrategy();
                        signUpProcessor.processSignUp();
                    }
                    case "exit" -> {
                        System.out.println("Exiting application...");
                        loop = false; // Exit the loop and end the application
                    }
                    default -> System.out.println("Invalid input. Please choose a valid option.\n");
                }
            }
        }
    }
}
