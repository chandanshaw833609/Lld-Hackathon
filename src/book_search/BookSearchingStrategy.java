package book_search;

import book.Book;

import java.util.List;

public interface BookSearchingStrategy {
    List<Book> searchBook(String searchOptions);
}
