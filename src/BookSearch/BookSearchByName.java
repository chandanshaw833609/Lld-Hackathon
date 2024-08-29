package BookSearch;

import java.util.List;
import java.util.Optional;

public class BookSearchByName implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchInput) {
        BookMgr bookMgr = BookMgr.getBookMgr();
        List<Book> allBooks = bookMgr.getAllBook();
        return allBooks
                .stream()
                .filter(book -> book
                        .getName()
                        .toLowerCase()
                        .contains(searchInput.toLowerCase()))
                .toList();
    }
}
