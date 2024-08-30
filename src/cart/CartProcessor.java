package cart;

import BookSearch.Book;
import BookSearch.BookManager;
import UserModule.User;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartProcessor {

    private BookManager bookManager;

    public CartProcessor()
    {
        this.bookManager = BookManager.getBookMgr();
    }

    public void addBooksToCart(User user)
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
    public void viewCart(User user)
    {
        if(user.getCart().getBooks().isEmpty())
        {
            System.out.println(user.getName() + " Your Cart is empty");
        }
        if(user.getCart()!=null)
        {
            user.getCart().getBooks().stream().forEach((book)-> System.out.println(book.toString()));
        }
    }
    public void deleteBooksFromCart(User user, List<Book> booksToDelete) {
        List<Book> updatedBooks = user.getCart().getBooks().stream()
                .filter(book -> !booksToDelete.contains(book))
                .collect(Collectors.toList());
        // Update the cart with the filtered list of books
        user.getCart().setBooks(updatedBooks);
    }
}
