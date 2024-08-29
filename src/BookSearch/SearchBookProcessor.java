package BookSearch;

import java.util.List;
import java.util.Scanner;

public class SearchBookProcessor {
    private static SearchBookProcessor searchBookProcessor;

    private BookSearchingStrategy bookSearchingStrategy;

    private final Scanner scanner;

    private SearchBookProcessor(){
        scanner = new Scanner(System.in);
    }

    public static SearchBookProcessor getSearchBookProcessor() {
        if (searchBookProcessor == null) {
            return new SearchBookProcessor();
        }
        return searchBookProcessor;
    }


    public void processSearchBook() {
        System.out.println("For searching by name enter - 1");
        System.out.println("For searching by author enter - 2");
        System.out.println("For searching by category enter - 3");

        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1 -> bookSearchingStrategy = new BookSearchByName();
            case 2 -> bookSearchingStrategy = new BookSearchByAuthor();
            case 3 -> bookSearchingStrategy = new BookSearchByCategory();
            default -> System.out.println("Enter a valid input");
        }

        System.out.print("Enter your search input : ");
        String searchInput = scanner.nextLine();
        List<Book> bookList = bookSearchingStrategy.searchBook(searchInput);
        bookList.forEach(book -> System.out.println(book.toString()));
        System.out.println();
    }


}
