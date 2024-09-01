package UserModule;

import BookSearch.Book;
import BookSearch.BookCategory;
import BookSearch.BookCategoryManager;
import BookSearch.BookManager;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SellerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();
    private final BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();


    public void processViewSalesHistoryRequest(Seller seller) {
        Map<String, Integer> bookSaleRecord = seller.getBookSaleMap();
        if (bookSaleRecord.isEmpty()) {
            System.out.println("You have not any sale history...\n");
            return;
        }

        double totalProfit = 0;
        System.out.println(seller.getName() + " sales are listed below : ");
        for (String bookId : bookSaleRecord.keySet()) {
            Book book = bookManager.getBook(bookId);
            int salesCount = bookSaleRecord.get(bookId);
            double profit = book.getPrice() * salesCount;
            totalProfit += profit;
            System.out.println("Book Name -> " + book.getName() + ", Sales Count: " + salesCount + ", Profit: Rs."+profit);
        }
        System.out.println("Your total profit Rs." + totalProfit);
        System.out.println();
    }

    public void processViewInventoryRequest(Seller seller) {
        List<String> inventory = seller.getInventory();

        if (inventory.isEmpty()) {
            System.out.println(seller.getName() + ", your inventory is empty!!!\n");
            return;
        }

        // otherwise show his inventory
        System.out.println(seller.getName() + ", your inventory -> ");
        for (String bookId : inventory) {
            Book book = bookManager.getBook(bookId);
            System.out.println(book.toString());
        }

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
        seller.updateInventory(book.getBookId());
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
