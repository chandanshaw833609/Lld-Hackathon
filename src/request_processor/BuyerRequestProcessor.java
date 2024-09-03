package request_processor;

import book.Book;
import book.BookManager;
import book_category.BookCategory;
import book_category.BookCategoryManager;
import buyer.CartItem;
import payment.PaymentProcessor;
import buyer.Buyer;
import buyer.PurchaseHistory;
import seller.Seller;
import user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BuyerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();
    private final BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();

    private final PaymentProcessor paymentProcessor = new PaymentProcessor();

    private final UserManager userManager = UserManager.getInstance();


    private final Scanner scanner = new Scanner(System.in);

    public void processBookBrowseRequest(Buyer buyer) {
        List<BookCategory> bookCategoryList = bookCategoryManager.getAllCategory();
        System.out.println("List of all the category -> ");
        bookCategoryList.forEach(bookCategory -> System.out.println("-> " + bookCategory.getName()));

        System.out.println("\nEnter category name for browsing -> ");
        Scanner scanner = new Scanner(System.in);
        String categoryName = scanner.nextLine();
        BookCategory bookCategory = bookCategoryManager.getCategoryByName(categoryName);

        if (bookCategory == null || bookCategory.getBooks().isEmpty()) {
            System.out.println("Sorry, there is no books in this category!!!\n");
            return;
        }


        System.out.println("\nList of all the books in '" + categoryName + "' ->" );
        for (String bookId : bookCategory.getBooks()) {
            Book book = bookManager.getBook(bookId);
            //show all the books
            System.out.println("-> " + book.toString());
        }
        processAddToCartRequest(buyer);
    }


    public void processBookSearchRequest(Buyer buyer) {
        System.out.println("Enter your search query -> ");
        String query = scanner.nextLine();
        List<Book> allBooks = bookManager.getAllBook();

        List<Book> searchResult = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(query) || book.getName().toLowerCase().contains(query)) {
                searchResult.add(book);
            }
        }


        if (searchResult.isEmpty()) {
            System.out.println("Sorry, no books found...");
            System.out.println("Enter a different search query!!!\n");
            return;
        }


        System.out.println("Search result for query " + "'" + query + "' -> " );
        searchResult.forEach(book -> System.out.println("-> " + book.toString()));

        processAddToCartRequest(buyer);
    }

    public void processAddToCartRequest(Buyer buyer) {
        String option;
        do {
            System.out.println("\nDo you want to add a book to your cart? Enter yes or no -> ");
            option = scanner.nextLine();

            if (option.equalsIgnoreCase("yes")) {
                System.out.println("Enter book name to add into cart -> ");
                String bookName = scanner.nextLine();
                Book book = bookManager.getBookByName(bookName);

                List<CartItem> cartItems = buyer.getCartItems();
                for (CartItem cartItem : cartItems) {
                    String bookId = cartItem.bookId();
                    if (book.getBookId().equalsIgnoreCase(bookId)) {
                        System.out.println(book.getName() + ", already in the cart!!!\n");
                        return;
                    }
                }

                if (book != null) {
                    CartItem cartItem = new CartItem(book.getBookId(), book.getPrice());
                    buyer.updateCartItems(cartItem);
                    System.out.println(book.getName() + " has been added to your cart!!!\n");
                } else {
                    System.out.println("Please enter the book name correctly!!!\n");
                }
            }
        } while (option.equalsIgnoreCase("yes"));
    }

    public void processViewCartRequest(Buyer buyer) {
        List<CartItem> cartItems = buyer.getCartItems();

        if (cartItems.isEmpty()){
            System.out.println("Cart is empty...\n");
            return;
        }

        //otherwise show all the books in the user's cart
        System.out.println(buyer.getName() + ", your cart items are listed below -> ");

        for (CartItem cartItem : cartItems) {
            String bookId = cartItem.bookId();
            Book book = bookManager.getBook(bookId);
            System.out.println("-> " + book.toString());
        }
        System.out.println();
    }

    public void processViewPurchaseHistoryRequest(Buyer buyer) {
        List<PurchaseHistory> purchaseHistories  = buyer.getHistory();

        if (purchaseHistories.isEmpty()) {
            System.out.println(buyer.getName() + ", your purchase history is empty...\n");
            return;
        }

        //otherwise show his cart books
        System.out.println(buyer.getName() + ", your purchase history is listed below ->");

        for (PurchaseHistory purchaseHistory : purchaseHistories) {
            List<String> books = purchaseHistory.books();
            for (String bookId : books) {
                Book book = bookManager.getBook(bookId);
                System.out.println("-> " + book.toString());
            }
        }
        System.out.println();
    }


    public void processPaymentRequest(Buyer buyer) {
        List<CartItem> cartItems = buyer.getCartItems();

        if (cartItems.isEmpty()) {
            System.out.println("Please add something to cart for buying!!!!\n");
            return;
        }

        double amount = 0;
        List<String> cartItemsBook = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            String bookId = cartItem.bookId();
            cartItemsBook.add(bookId);
            amount += cartItem.amount();
        }

        // otherwise continue with payment
        paymentProcessor.setPaymentStrategy();
        paymentProcessor.processPayment(amount);


        // update buyers purchase history
        PurchaseHistory purchaseHistory = new PurchaseHistory(cartItemsBook);
        buyer.getHistory().add(purchaseHistory);

        // add or update sale to each of the seller
        updateSaleRecord(cartItemsBook);

        // empty buyers cart
        cartItems.clear();

    }



    private void updateSaleRecord(List<String> bookIdList) {
        for (String bookId : bookIdList) {
            // first get each seller from book object
            // and then update his sales
            Book book = bookManager.getBook(bookId);
            Seller seller = (Seller) userManager.getUserById(book.getSellerId());
            if (seller != null) {
                seller.recordSale(bookId);
            }
        }
    }
}
