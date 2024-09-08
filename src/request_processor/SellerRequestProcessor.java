package request_processor;

import book.Book;
import book.BookCategory;
import book.BookManager;
import seller.SalesRecord;
import seller.SalesRecordManager;
import user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SellerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();
    private final SalesRecordManager salesRecordManager = SalesRecordManager.getInstance();

    public void processViewSalesRecordRequest(User seller) {
        List<SalesRecord> salesRecordList = salesRecordManager.getSalesRecordBySeller(seller.getId());
        if (salesRecordList.isEmpty()) {
            System.out.println("You have not any sale history...\n");
            return;
        }

        double totalProfit = 0;
        System.out.println(seller.getName() + ", your sales are listed below -> ");

        for (SalesRecord record : salesRecordList) {
            Book book = record.getBook();
            int timesSold = record.getTimesSold();
            double profit = book.getPrice() * timesSold;
            totalProfit += profit;
            System.out.println("Book -> " + book.getTitle() + ", Sales Count: " + timesSold + ", Profit: Rs" + profit);
        }
        System.out.println("Your total profit: " +totalProfit);
        System.out.println();
    }



    public void processViewInventoryRequest(User seller) {
        List<Book> inventory = bookManager.getBookBySeller(seller.getId());

        if (inventory.isEmpty()) {
            System.out.println(seller.getName() + ", your inventory is empty!!!\n");
            return;
        }

        // otherwise show his inventory
        System.out.println(seller.getName() + ", your inventory -> ");
        inventory.forEach(book -> System.out.println(book.toString()));
        System.out.println();
    }

    public void processAddBookRequest(User seller) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book name : ");
        String bookName = scanner.nextLine();

        Book registeredBook = bookManager.getBookByNameAndSeller(bookName, seller.getId());

        if (registeredBook != null) {
            System.out.println("Enter different book name...");
            System.out.println("Book with book name " + bookName + " exist already!!!\n");
            return;
        }

        System.out.print("Enter author name : ");
        String author = scanner.nextLine();

        System.out.print("Enter price for the book : ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        List<BookCategory> lies = List.of(BookCategory.values());
        for (BookCategory categoryEnum : BookCategory.values()) {
            System.out.println(categoryEnum.ordinal() + " -> " + categoryEnum.getName());
        }

        System.out.println("Choose category by entering number -> ");
        int category = scanner.nextInt();
        scanner.nextLine();

        BookCategory bookCategory = Arrays
                .stream(BookCategory.values())
                .filter(bookCategoryEnum1 -> bookCategoryEnum1.ordinal() == category)
                .findFirst()
                .orElse(null);

        if (bookCategory == null) {
            System.out.println("choose a valid category...");
            System.out.println("Book adding request failed...\n");
            return;
        }

        Book book = new Book(bookName, author, bookCategory, price, seller.getId());
        bookManager.addBook(book);

        System.out.println("Book added successfully\n");
    }

}
