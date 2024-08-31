import BookSearch.Book;
import BookSearch.BookCategory;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;
import UserModule.*;

import java.util.List;
import java.util.Scanner;

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
