package book_search;

import book.Book;
import book.BookManager;

import java.util.List;

public class BookSearchByName implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchOptions) {
        BookManager bookManager = BookManager.getInstance();
        List<Book> allBooks = bookManager.getAllBook();
        return allBooks
                .stream()
                .filter(book -> book
                        .getTitle()
                        .toLowerCase()
                        .contains(searchOptions.toLowerCase()))
                .toList();
    }
}
