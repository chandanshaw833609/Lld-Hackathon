package UserModule;

import BookSearch.Book;
import BookSearch.BookCategory;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;

import java.util.List;
import java.util.Scanner;

public class SellerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();
    private final BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();

    public void displaySellerMenu(Seller seller) {
        System.out.println("For adding a book enter -> 1");
        System.out.println("For viewing sales enter -> 3");
        System.out.println("For logout enter logout -> logout");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        switch (option) {
            case "1" -> processAddBookRequest(seller);
            case "2" -> processViewSalesRequest(seller);
            case "logout" -> {
                seller = null;
                System.out.println("Logged out successfully...\n");
            }
            default -> System.out.println("Choose a valid option...");
        }
    }

    public void processViewSalesRequest(Seller seller) {
        System.out.println(seller.getName() + " sales are listed below : ");
        seller.getSales().forEach(sale -> System.out.println(sale.toString()));
        System.out.println();
    }

    public void processAddBookRequest(Seller seller) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book name : ");
        String bookName = scanner.nextLine();

        System.out.print("Enter author name : ");
        String author = scanner.nextLine();

        System.out.print("Enter price for the book : ");
        double price = scanner.nextDouble();

        scanner.nextLine();

        List<BookCategory> bookCategories = bookCategoryManager.getAllCategory();
        bookCategories.forEach(bookCategory -> System.out.println(bookCategory.getName()));
        System.out.println();
        System.out.println("Choose one of the category -> ");
        String category = scanner.nextLine();

        BookCategory bookCategory = bookCategoryManager.getCategoryByName(category);

        if (bookCategory == null) {
            System.out.println("choose a valid category...");
            System.out.println("Book adding request failed...\n");
            return;
        }

        Book book = new Book(bookName, author, bookCategoryManager.getCategoryByName(category),price, seller.getId());
        bookManager.addBook(book);
        System.out.println("Book added successfully\n");
    }

//    public void removeBook(Seller seller) {
//        List<Book> sellerAllBooks = bookManager
//                .getAllBook()
//                .stream()
//                .filter(book -> book
//                        .getSellerId()
//                        .equals(seller.getId()))
//                .toList();
//        sellerAllBooks.forEach(book -> System.out.println(book.toString()));
//        System.out.println();
//        System.out.println("choose book you want to remove -> ");
//    }


}
