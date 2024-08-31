package UserModule;

import BookSearch.Book;
import BookSearch.BookManager;
import BookSearch.BookSearchProcessor;
import PaymentModule.PaymentProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();
    private final BookSearchProcessor bookSearchProcessor = new BookSearchProcessor();

    private final PaymentProcessor paymentProcessor = new PaymentProcessor();

    private final UserManager userManager = UserManager.getInstance();


    public void processBookSearchRequest(Buyer buyer) {
        bookSearchProcessor.setBookSearchingStrategy();
        List<Book> searchedBooks =  bookSearchProcessor.processSearchBookRequest();

        if (searchedBooks == null || searchedBooks.isEmpty()) {
            System.out.println("Could not find any book...");
            System.out.println("Enter a different search input...\n");
            return;
        }
        // printing all the book related to user's search
        searchedBooks.forEach(book -> System.out.println(book.toString()));
        System.out.println();

        // showing option for adding book into cart
        processAddToCartRequest(buyer);
    }

    public void processAddToCartRequest(Buyer buyer) {
        System.out.println("For adding book to cart enter book name -> ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();
        Book book = bookManager.getBookByName(bookName);
        if (book == null) {
            System.out.println("Invalid book name");
            return;
        }

        // otherwise add the book in the previous book list of cart
        // and updating the total amount
        double amount = buyer.getCart().getTotalCartAmount() + book.getPrice();
        buyer.getCart().getBooks().add(book);
        buyer.getCart().setTotalCartAmount(amount);
        System.out.println(bookName + " has been added to your cart...\n");
    }

    public void processViewCartRequest(Buyer buyer) {
        List<Book> cartBooks = buyer.getCart().getBooks();

        if (cartBooks.isEmpty()){
            System.out.println("Cart is empty...");
            return;
        }
        //otherwise show all the books in the user's cart

        System.out.println(buyer.getName() + " cart details ar listed below");
        buyer.getCart().getBooks().forEach(book -> System.out.println(book.toString()));
        System.out.println();
    }

    public void processViewPurchaseHistoryRequest(Buyer buyer) {
        List<PurchaseHistory> purchaseHistories  = buyer.getHistory();
        if (purchaseHistories.isEmpty()) {
            System.out.println(buyer.getName() + "has no purchase history...\n");
        }
        System.out.println(buyer.getName() + " purchase his is listed below\n");
        purchaseHistories.
                forEach(purchaseHistory -> purchaseHistory
                        .getBooks()
                        .forEach(book -> System.out.println(book.toString())));
        System.out.println();
    }


    public void processPaymentRequest(Buyer buyer) {
        double amount = buyer.getCart().getTotalCartAmount();
        List<Book> cartBooks = new ArrayList<>(buyer.getCart().getBooks());
        if (cartBooks.isEmpty()) {
            System.out.println("Please add something to cart for buying!!!!");
            return;
        }

        // otherwise continue with payment
        paymentProcessor.setPaymentStrategy();
        paymentProcessor.processPayment(amount);

        // update the buyers cart
        buyer.getCart().getBooks().clear();
        buyer.getCart().setTotalCartAmount(0);


        // update buyers purchase history
        updateBuyersPurchaseHistory(buyer,cartBooks);

        // add or update sale to each of the seller
        updateSellersSale(cartBooks);


    }

    public void processBookBrowseRequest(Buyer buyer) {
        List<Book> bookList = bookManager.getAllBook();
        bookList.forEach(book -> System.out.println(book.toString()));
        if (!bookList.isEmpty()) {
            processAddToCartRequest(buyer);
        }
    }

    public void updateBuyersPurchaseHistory(Buyer buyer, List<Book> cartBooks) {
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setBooks(cartBooks);
        buyer.getHistory().add(purchaseHistory);
    }

    public void updateSellersSale(List<Book> cartBooks) {
        for (Book book : cartBooks) {
            // first get each seller from book object
            // and then update his sales list
            // or create new sale and add in the sales list
            Seller seller = (Seller) userManager.getUserById(book.getSellerId());
            List<Sale> saleList = seller.getSales();
            if (saleList.isEmpty()) {
                Sale newSale = new Sale(book);
                seller.setSales(newSale);
                return;
            }
            for (Sale sale : saleList) {
                if (sale.getBook() == book) {
                    double totalProfit = sale.getTotalProfit() + book.getPrice();
                    sale.setTotalProfit(totalProfit);
                    sale.setTotalPurchase(sale.getTotalPurchase()+1);
                } else {
                    Sale newSale = new Sale(book);
                    seller.setSales(newSale);
                }
            }
        }
    }
}