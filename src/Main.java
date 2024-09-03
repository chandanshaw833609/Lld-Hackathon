import book.Book;
import book_category.BookCategory;
import book_category.BookCategoryManager;
import book.BookManager;
import buyer.Buyer;
import seller.Seller;
import user.*;

public class Main {

    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        UserManager userManager = UserManager.getInstance();
        BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();
        BookManager bookManager = BookManager.getInstance();

        User admin = new User(Role.ADMIN);
        admin.setName("admin");
        admin.setUsername("admin");
        admin.setPassword("admin");
        userManager.saveUser(admin);

        Seller seller = new Seller();
        seller.setName("seller");
        seller.setPassword("seller");
        seller.setUsername("seller");
        userManager.saveUser(seller);

        Buyer buyer = new Buyer();
        buyer.setName("buyer");
        buyer.setUsername("buyer");
        buyer.setPassword("buyer");
        userManager.saveUser(buyer);

        BookCategory bookCategory1 = new BookCategory("Fiction");
        BookCategory bookCategory2 = new BookCategory("Drama");

        bookCategoryManager.addBookCategory(bookCategory1);
        bookCategoryManager.addBookCategory(bookCategory2);

        Book book1 = new Book("Harry Potter", "JK Rollings", bookCategory1, 1000.00, seller.getId());
        bookManager.addBook(book1);
        seller.updateInventory(book1.getBookId());
        bookCategory1.addBook(book1.getBookId());

        Book book2 = new Book("Macbeth", "William Shakespeare", bookCategory2, 1500.00, seller.getId());
        bookManager.addBook(book2);
        seller.updateInventory(book2.getBookId());
        bookCategory2.addBook(book2.getBookId());

        libraryManagementSystem.displayMenu();
        
    }
}
