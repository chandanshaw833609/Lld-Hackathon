package BookSearch;

import java.util.List;

public interface BookSearchingStrategy {
    List<Book> searchBook(String searchField);
}
