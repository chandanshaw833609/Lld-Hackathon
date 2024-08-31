package BookSearch;

import java.util.*;

public class BookCategoryManager {

    private static BookCategoryManager bookCategoryManagerInstance;
    private final Map<String, BookCategory> bookCategoryMap;

    private final Scanner scanner;
    private BookCategoryManager() {
        bookCategoryMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static BookCategoryManager getInstance() {
        if (bookCategoryManagerInstance == null) {
            bookCategoryManagerInstance = new BookCategoryManager();
        }
        return bookCategoryManagerInstance;
    }

    public BookCategory getBookCategory(String bookCategoryId) {
        return bookCategoryMap.get(bookCategoryId);
    }

    public void addBookCategory(BookCategory bookCategory) {
        bookCategoryMap.put(bookCategory.getBookCategoryId(), bookCategory);
        System.out.println("Category added successfully\n");
    }

    public List<BookCategory> getAllCategory() {
        System.out.println("Total book categories are " + bookCategoryMap.size());
        return new ArrayList<>(bookCategoryMap.values());
    }

    public BookCategory getCategoryByName(String bookCategoryName) {
            return bookCategoryMap
                    .values()
                    .stream()
                    .filter(category -> category
                            .getName()
                            .equalsIgnoreCase(bookCategoryName))
                    .findFirst()
                    .orElse(null);
    }

}
