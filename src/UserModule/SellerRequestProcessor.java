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


    public void processViewSalesHistoryRequest(Seller seller) {
        List<Sale> saleList = seller.getSalesHistory();
        if (saleList.isEmpty()) {
            System.out.println("You have not any sale history...\n");
            return;
        }
        System.out.println(seller.getName() + " sales are listed below : ");
        seller.getSalesHistory().forEach(sale -> System.out.println("-> "+sale.toString()));
        System.out.println();
    }

    public void processViewInventoryRequest(Seller seller) {
        List<Book> inventory = seller.getInventory();

        if (inventory.isEmpty()) {
            System.out.println(seller.getName() + ", your inventory is empty!!!\n");
            return;
        }

        // otherwise show his inventory
        System.out.println(seller.getName() + ", your inventory -> ");
        inventory.forEach(book -> System.out.println("-> " + book.toString()));
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
        bookCategories.forEach(bookCategory -> System.out.println("-> "+bookCategory.getName()));
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

        //update seller inventory
        seller.updateInventory(book);
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
