package BookSearch;

import java.util.*;

public class BookMgr {
    private final Map<String, Book> booksMap;
    private final BookCategoryMgr bookCategoryMgr;
    private static BookMgr bookMgr;

    private final Scanner scanner;

    private BookMgr() {
        booksMap = new HashMap<>();
        scanner = new Scanner(System.in);
        bookCategoryMgr = BookCategoryMgr.getBookCategoryMgr();
    }

    public static BookMgr getBookMgr() {
        if (bookMgr == null) {
            bookMgr =  new BookMgr();
        }
        return bookMgr;
    }

    public Book getBook(String bookId) {
        if (booksMap.containsKey(bookId)){
            return booksMap.get(bookId);
        }
        throw new RuntimeException("This book does not exist");
    }

    public void addBook() {
        System.out.print("Enter book name : ");
        String bookName = scanner.nextLine();

        System.out.print("Enter author name : ");
        String author = scanner.nextLine();

        List<BookCategory> bookCategories = bookCategoryMgr.getAllCategory();
        bookCategories.forEach(bookCategory -> System.out.println(bookCategory.getName()));

        System.out.println("enter the name of category -> ");
        String category = scanner.nextLine();

        BookCategory bookCategory = bookCategoryMgr.getCategoryByName(category);

        if (bookCategory == null) {
            System.out.println("choose a valid category...");
            addBook();
            return;
        }

        Book book = new Book(bookName, author, bookCategoryMgr.getCategoryByName(category));
        booksMap.put(book.getBookId(), book);
        System.out.println("Book added successfully");
        System.out.println();
    }

    public List<Book> getAllBook() {
        return new ArrayList<>(booksMap.values());
    }
}
