package cart;

import BookSearch.Book;
import BookSearch.BookManager;
import UserModule.Buyer;
import UserModule.User;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartProcessor {

    private final BookManager bookManager;

    public CartProcessor()
    {
        this.bookManager = BookManager.getInstance();
    }

    public void addBooksToCart(Buyer user)
    {
        System.out.println("Add book name to add to cart");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();
        Book book = bookManager.getBookByName(bookName);
        user.getCart().getBooks().add(book);
        double amount = user.getCart().getTotalCartAmount()+book.getPrice();
        user.getCart().setTotalCartAmount(amount);

        if(user.getCart().getBooks().isEmpty())
        {
            System.out.println("Cart is empty");
        }
    }
    public void viewCart(Buyer user)
    {
        if (user.getCart().getBooks().size() == 0)
        {
            System.out.println(user.getName() + " Your Cart is empty");
        }
        else
        {
            user.getCart().getBooks().forEach(book-> System.out.println(book.toString()));
        }
    }
    public void deleteBooksFromCart(Buyer user, List<Book> booksToDelete) {
        List<Book> updatedBooks = user.getCart().getBooks().stream()
                .filter(book -> !booksToDelete.contains(book))
                .collect(Collectors.toList());
        user.getCart().setBooks(updatedBooks);
    }
}
