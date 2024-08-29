package BookSearch;

import java.util.List;
import java.util.stream.Collectors;

public class BookSearchByCategory implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchInput) {
        BookMgr bookMgr = BookMgr.getBookMgr();
        List<Book> allBooks = bookMgr.getAllBook();
        return allBooks
                .stream()
                .filter(book1 -> book1
                        .getBookCategory()
                        .getName()
                        .equalsIgnoreCase(searchInput))
                .toList();
    }
}
