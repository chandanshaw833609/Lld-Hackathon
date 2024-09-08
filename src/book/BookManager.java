package book;

import java.util.*;

public class BookManager {
    private final Map<String, Book> booksMap;
    private static BookManager bookManager;


    private BookManager() {
        booksMap = new HashMap<>();
    }

    public static BookManager getInstance() {
        if (bookManager == null) {
            bookManager =  new BookManager();
        }
        return bookManager;
    }

    public Book getBook(String bookId) {
        if (booksMap.containsKey(bookId)){
            return booksMap.get(bookId);
        }
        throw new RuntimeException("This book does not exist");
    }

    public Book getBookByName(String bookName) {
       Optional<Book> optionalBook = booksMap.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(bookName)).findFirst();
        return optionalBook.orElse(null);
    }

    public void addBook(Book book) {
        booksMap.put(book.getBookId(), book);
    }

    public List<Book> getAllBook() {
        return new ArrayList<>(booksMap.values());
    }

    public List<Book> getBookBySeller(String sellerId) {
        return booksMap
                .values()
                .stream()
                .filter(book -> book.getSellerId().equals(sellerId))
                .toList();
    }

    public List<Book> getBookByCategory(BookCategory bookCategory) {
        return booksMap.
                values()
                .stream()
                .filter(book -> book.getBookCategory().ordinal() == bookCategory.ordinal())
                .toList();
    }

    public Book getBookByNameAndSeller(String bookName, String sellerId) {
        return booksMap.
                values()
                .stream()
                .filter(book -> book.getTitle().equals(bookName) && book.getSellerId().equals(sellerId))
                .findFirst()
                .orElse(null);
    }
}
