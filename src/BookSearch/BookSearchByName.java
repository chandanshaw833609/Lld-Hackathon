package BookSearch;

import java.util.List;

public class BookSearchByName implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchInput) {
        BookManager bookManager = BookManager.getBookMgr();
        List<Book> allBooks = bookManager.getAllBook();
        return allBooks
                .stream()
                .filter(book -> book
                        .getName()
                        .toLowerCase()
                        .contains(searchInput.toLowerCase()))
                .toList();
    }
}
