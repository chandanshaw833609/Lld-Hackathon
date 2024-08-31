package book_search;

import book.Book;
import book.BookManager;

import java.util.List;

public class BookSearchByCategory implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchOptions) {
        BookManager bookManager = BookManager.getInstance();
        List<Book> allBooks = bookManager.getAllBook();
        return allBooks
                .stream()
                .filter(book1 -> book1
                        .getBookCategory()
                        .getName()
                        .equalsIgnoreCase(searchOptions))
                .toList();
    }
}
