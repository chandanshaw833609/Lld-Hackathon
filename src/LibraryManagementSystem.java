import BookSearch.*;
import BookSearch.BookBrowseProcessor;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;
import BookSearch.BookSearchProcessor;
import PaymentModule.PaymentProcessor;
import PaymentModule.PaymentProcessor;
import UserModule.LoginProcessor;
import UserModule.PurchaseHistory;
import UserModule.SignUpProcessor;
import UserModule.User;
import cart.CartProcessor;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private static LibraryManagementSystem libraryManagementSystem;
    private final BookCategoryManager bookCategoryManager;
    private final BookManager bookManager;
    private final BookSearchProcessor bookSearchProcessor;
    private final LoginProcessor loginProcessor;
    private final SignUpProcessor signUpProcessor;
    private final BookBrowseProcessor bookBrowseProcessor;
    private final CartProcessor cartProcessor;

    private final PaymentProcessor paymentProcessor;

    private LibraryManagementSystem() {
        bookCategoryManager = BookCategoryManager.getBookCategoryMgr();
        bookManager = BookManager.getBookMgr();
        bookSearchProcessor = new BookSearchProcessor();
        loginProcessor = new LoginProcessor();
        signUpProcessor = new SignUpProcessor();
        bookBrowseProcessor = new BookBrowseProcessor();
        cartProcessor = new CartProcessor();
        paymentProcessor = new PaymentProcessor();
    }

    public static LibraryManagementSystem getInstance() {
        if (libraryManagementSystem == null) {
            libraryManagementSystem = new LibraryManagementSystem();
        }
        return libraryManagementSystem;
    }


    public User processLoginRequest() {
        loginProcessor.setLoginStrategy();
        return loginProcessor.processLogin();
    }

    public User processSignUpRequest() {
        signUpProcessor.setSignupStrategy();
        return signUpProcessor.processSignUp();
    }

    public void processBookBrowseRequest() {
        bookBrowseProcessor.browseBooks();
    }

    public void processAddBookRequest() {
        bookManager.addBook();
    }

    public List<Book> processBookSearchRequest() {
        bookSearchProcessor.setBookSearchingStrategy();
        List<Book> booksSearched = bookSearchProcessor.processSearchBookRequest();
        return booksSearched;
    }

    public void processAddCategoryRequest() {
        bookCategoryManager.addBookCategory();
    }

    public void processAddToCartRequest(User user)
    {
        this.cartProcessor.addBooksToCart(user);
    }

    public void processViewCartRequest(User user)
    {
        this.cartProcessor.viewCart(user);
    }

    public void processPaymentRequest(User user) {
        if(user.getCart().getBooks().size()!=0)
        {
            paymentProcessor.setPaymentStrategy();
            paymentProcessor.processPayment(user.getCart().getTotalCartAmount());
            System.out.println("Payment successfull YAYYY!!!");

            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setBooks(user.getCart().getBooks());
            user.getHistory().add(purchaseHistory);

            user.getCart().setTotalCartAmount(0);
            user.getCart().setBooks(new ArrayList<>());
        }
        else
        {
            System.out.println("Cart is empty , Hence not proceeding with payment");
        }

    }
}