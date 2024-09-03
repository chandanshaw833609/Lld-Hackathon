package book_search;

import book.Book;
import book.BookManager;
import book_category.BookCategory;
import book_category.BookCategoryManager;

import java.util.ArrayList;
import java.util.List;

public class BookSearchByCategory implements BookSearchingStrategy{

    @Override
    public List<Book> searchBook(String searchOption) {
        BookManager bookManager = BookManager.getInstance();
        BookCategoryManager bookCategoryManager = BookCategoryManager.getInstance();
        BookCategory bookCategory = bookCategoryManager.getCategoryByName(searchOption);
        List<Book> searchResult = new ArrayList<>();
        if (bookCategory != null) {
            for (String bookId : bookCategory.getBooks()) {
                Book book = bookManager.getBook(bookId);
                searchResult.add(book);
            }
        }
        return searchResult;
    }
}
