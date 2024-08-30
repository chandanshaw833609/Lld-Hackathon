package BookSearch;

import java.util.List;
import java.util.Scanner;

public class BookSearchProcessor {
    private BookSearchingStrategy bookSearchingStrategy;
    private final Scanner scanner = new Scanner(System.in);

    public void processSearchBookRequest() {
        System.out.print("Enter your search input : ");
        String searchInput = scanner.nextLine();
        List<Book> bookList = bookSearchingStrategy.searchBook(searchInput);
        bookList.forEach(book -> System.out.println(book.toString()));
        System.out.println();
    }

    public void setBookSearchingStrategy() {
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
        System.out.println();
    }


}
