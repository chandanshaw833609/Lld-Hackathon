import BookSearch.*;
import UserModule.LoginProcessor;
import UserModule.SignUpProcessor;
import UserModule.User;
import cart.CartProcessor;

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

    private LibraryManagementSystem() {
        bookCategoryManager = BookCategoryManager.getBookCategoryMgr();
        bookManager = BookManager.getBookMgr();
        bookSearchProcessor = new BookSearchProcessor();
        loginProcessor = new LoginProcessor();
        signUpProcessor = new SignUpProcessor();
        bookBrowseProcessor = new BookBrowseProcessor();
        cartProcessor = new CartProcessor();
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
}