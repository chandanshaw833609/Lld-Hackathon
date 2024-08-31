import book.Book;
import book.BookCategory;
import book_search.BookCategoryManager;
import book.BookManager;
import user.*;

public class Main {

    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        UserManager userManager = UserManager.getInstance();
        BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();
        BookManager bookManager = BookManager.getInstance();

        User admin = new Admin();
        admin.setName("admin");
        admin.setPassword("admin");
        userManager.saveUser(admin);

        User seller = new Seller();
        seller.setName("seller");
        seller.setPassword("seller");
        userManager.saveUser(seller);

        User buyer = new Buyer();
        buyer.setName("buyer");
        buyer.setPassword("buyer");
        userManager.saveUser(buyer);

        BookCategory bookCategory = new BookCategory("Fiction");
        bookCategoryManager.addBookCategory(bookCategory);

        Book book = new Book("Harry Potter", "JK Rollings", bookCategory, 100, seller.getId());
        bookManager.addBook(book);

        libraryManagementSystem.displayMenu();
        
    }
}
