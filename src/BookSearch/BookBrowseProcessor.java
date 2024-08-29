package BookSearch;

import java.util.List;
import java.util.Scanner;

public class BookBrowseProcessor {
    public void browseBooks() {
        BookCategoryMgr bookCategoryMgr = BookCategoryMgr.getBookCategoryMgr();
        List<BookCategory> categoryList = bookCategoryMgr.getAllCategory();
        if (categoryList.size() == 0) {
            System.out.println("There are no book available currently...");
            return;
        }

        categoryList.forEach(bookCategory -> System.out.println("-> "+bookCategory.getName()));
        System.out.println();
        System.out.println("For browsing a specific category enter its name -> ");
        Scanner scanner = new Scanner(System.in);
        String category = scanner.nextLine();

        BookSearchByCategory bookSearchByCategory = new BookSearchByCategory();
        List<Book> bookList =  bookSearchByCategory.searchBook(category);
        bookList.forEach(book -> System.out.println(book.toString()));
        System.out.println();
    }
}
