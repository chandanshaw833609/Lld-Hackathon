import BookSearch.BookBrowseProcessor;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;
import BookSearch.BookSearchProcessor;
import UserModule.LoginProcessor;
import UserModule.Role;
import UserModule.SignUpProcessor;

public class LibraryManagementSystem {
    private static LibraryManagementSystem libraryManagementSystem;
    private final BookCategoryManager bookCategoryManager;
    private final BookManager bookManager;
    private final BookSearchProcessor bookSearchProcessor;
    private final LoginProcessor loginProcessor;
    private final SignUpProcessor signUpProcessor;
    private final BookBrowseProcessor bookBrowseProcessor;

    private LibraryManagementSystem() {
        bookCategoryManager = BookCategoryManager.getBookCategoryMgr();
        bookManager = BookManager.getBookMgr();
        bookSearchProcessor = new BookSearchProcessor();
        loginProcessor = new LoginProcessor();
        signUpProcessor = new SignUpProcessor();
        bookBrowseProcessor = new BookBrowseProcessor();
    }

    public static LibraryManagementSystem getInstance() {
        if (libraryManagementSystem == null) {
            libraryManagementSystem = new LibraryManagementSystem();
        }
        return libraryManagementSystem;
    }


    public Role processLoginRequest() {
        loginProcessor.setLoginStrategy();
        return loginProcessor.processLogin();
    }

    public Role processSignUpRequest() {
        return signUpProcessor.processSignUp();
    }

    public void processBookBrowseRequest() {
        bookBrowseProcessor.browseBooks();
    }

    public void processAddBookRequest() {
        bookManager.addBook();
    }

    public void processBookSearchRequest() {
        bookSearchProcessor.setBookSearchingStrategy();
        bookSearchProcessor.processSearchBookRequest();
    }

    public void processAddCategoryRequest() {
        bookCategoryManager.addBookCategory();
    }
}