package BookSearch;

import java.util.List;

public class BookSearchByAuthor implements BookSearchingStrategy{
    @Override
    public List<Book> searchBook(String searchOptions) {
        BookManager bookManager = BookManager.getInstance();
        List<Book> allBooks = bookManager.getAllBook();
        return allBooks
                .stream()
                .filter(book1 -> book1
                        .getAuthor()
                        .toLowerCase()
                        .contains(searchOptions.toLowerCase()))
                .toList();
    }
}
