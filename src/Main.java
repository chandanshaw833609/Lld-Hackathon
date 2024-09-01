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

        Admin admin = new Admin();
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

        BookCategory bookCategory = new BookCategory("Fiction");
        bookCategoryManager.addBookCategory(bookCategory);

        Book book = new Book("Harry Potter", "JK Rollings", bookCategory, 100, seller.getId());
        bookManager.addBook(book);
        seller.updateInventory(book);

        libraryManagementSystem.displayMenu();
        
    }
}
