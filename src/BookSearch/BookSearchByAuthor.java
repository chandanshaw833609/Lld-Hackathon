package BookSearch;

import java.util.List;

public class BookSearchByAuthor implements BookSearchingStrategy{
    @Override
    public List<Book> searchBook(String searchInput) {
        BookMgr bookMgr = BookMgr.getBookMgr();
        List<Book> allBooks = bookMgr.getAllBook();
        return allBooks
                .stream()
                .filter(book1 -> book1
                        .getAuthor()
                        .toLowerCase()
                        .contains(searchInput.toLowerCase()))
                .toList();
    }
}
