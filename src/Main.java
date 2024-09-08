import book.Book;
import book.BookCategory;
import book.BookManager;
import buyer.Buyer;
import seller.Seller;
import user.*;

public class Main {

    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        UserManager userManager = UserManager.getInstance();
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


        Book book1 = new Book("Harry Potter", "JK Rollings", BookCategory.SCIENCE_FICTION, 1000.00, seller.getId());
        bookManager.addBook(book1);


        Book book2 = new Book("Macbeth", "William Shakespeare", BookCategory.LITERATURE_FICTION, 1500.00, seller.getId());
        bookManager.addBook(book2);

        libraryManagementSystem.displayMenu();
        
    }
}
