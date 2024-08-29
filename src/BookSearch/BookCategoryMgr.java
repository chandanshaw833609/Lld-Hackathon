package BookSearch;

import java.util.*;

public class BookCategoryMgr {

    private static BookCategoryMgr bookCategoryMgrInstance;
    private final Map<String, BookCategory> bookCategoryMap;

    private final Scanner scanner;
    private BookCategoryMgr() {
        bookCategoryMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static BookCategoryMgr getBookCategoryMgr() {
        if (bookCategoryMgrInstance == null) {
            bookCategoryMgrInstance = new BookCategoryMgr();
        }
        return bookCategoryMgrInstance;
    }

    public BookCategory getBookCategory(String bookCategoryId) {
        return bookCategoryMap.get(bookCategoryId);
    }

    public void addBookCategory() {
        System.out.print("Enter Book Category Name : ");
        String categoryName = scanner.nextLine();
        BookCategory bookCategory = new BookCategory(categoryName);
        bookCategoryMap.put(bookCategory.getBookCategoryId(), bookCategory);
        System.out.println("Category added successfully");
        System.out.println();
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
