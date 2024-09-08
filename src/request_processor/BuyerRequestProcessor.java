package request_processor;

import book.Book;
import book.BookCategory;
import book.BookManager;
import buyer.*;
import payment.PaymentProcessor;
import seller.SalesRecord;
import seller.SalesRecordManager;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class BuyerRequestProcessor {
    private final BookManager bookManager = BookManager.getInstance();

    private final PaymentProcessor paymentProcessor = new PaymentProcessor();

    private final CartItemManager cartItemManager = CartItemManager.getInstance();

    private final OrderManager orderManager = OrderManager.getInstance();

    private final SalesRecordManager salesRecordManager = SalesRecordManager.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void processBookBrowseRequest(User buyer) {

        System.out.println("\nList of categories -> \n");
        for (BookCategory bookCategory : BookCategory.values()) {
            System.out.println(bookCategory.ordinal() + " -> " + bookCategory.getName());
        }

        System.out.println("\nEnter category number for browsing -> ");
        Scanner scanner = new Scanner(System.in);
        int categoryNum = scanner.nextInt();
        scanner.nextLine();

        BookCategory bookCategory = Arrays.stream(BookCategory.values())
                .filter(category -> category.ordinal() == categoryNum)
                .findFirst()
                .orElse(null);

        if (bookCategory != null) {
            List<Book> bookList = bookManager.getBookByCategory(bookCategory);
            if (!bookList.isEmpty()) {
                System.out.println("\nList of book in the category '" + bookCategory.getName() + "' -> ");
                bookList.forEach(book -> System.out.println("-> " + book.toString()));
                System.out.println();
                processAddToCartRequest(buyer);
                return;
            }
        }

        System.out.println("Sorry, there is no books in this category!!!\n");


    }


    public void processBookSearchRequest(User buyer) {
        System.out.println("Enter your search query -> ");
        String query = scanner.nextLine();
//        query = query.toLowerCase();
        List<Book> allBooks = bookManager.getAllBook();


//        for (Book book : allBooks) {
//            if (book.getAuthor().toLowerCase().contains(query)
//                    || book.getTitle().toLowerCase().contains(query)) {
//                searchResult.add(book);
//            }
//        }
//        String finalQuery = query;
        List<Book> searchResult  = allBooks.stream()
                .filter(book -> book.getBookCategory().getName().toLowerCase().contains(query.toLowerCase())
                   || book.getTitle().toLowerCase().contains(query.toLowerCase())
                   || book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .toList();



        if (searchResult.isEmpty()) {
            System.out.println("Sorry, no books found...");
            System.out.println("Enter a different search query!!!\n");
            return;
        }


        System.out.println("Search result for query " + "'" + query + "' -> " );
        searchResult.forEach(book -> System.out.println("-> " + book.toString()));

        processAddToCartRequest(buyer);
    }

    public void processAddToCartRequest(User buyer) {
        String option;
        do {
            System.out.println("\nDo you want to add a book to your cart? Enter yes or no -> ");
            option = scanner.nextLine();

            if (option.equalsIgnoreCase("yes")) {
                System.out.println("Enter book name to add into cart -> ");
                String bookName = scanner.nextLine();
                Book book = bookManager.getBookByName(bookName);


                List<CartItem> cartItemList = cartItemManager.getCartItemByUser(buyer.getId());

                for (CartItem item : cartItemList) {
                    if (item.getBook().getBookId().equals(book.getBookId())) {
                        System.out.println(book.getTitle() + ", already in the cart!!!\n");
                        return;
                    }
                }

                CartItem cartItem = new CartItem(buyer.getId(), book);
                cartItemManager.saveCartItem(cartItem);
                System.out.println(book.getTitle() + ", has been added to your cart.");

            }
        } while (option.equalsIgnoreCase("yes"));
    }

    public void processViewCartRequest(User buyer) {
        List<CartItem> cartItemList = cartItemManager.getCartItemByUser(buyer.getId());

        if (cartItemList.isEmpty()){
            System.out.println("Cart is empty...\n");
            return;
        }

        //otherwise show all the books in the user's cart
        System.out.println(buyer.getName() + ", your cart items are listed below -> ");

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            System.out.println("-> " + book.toString());
        }
        System.out.println();
    }

    public void processViewOrderHistoryRequest(User buyer) {
//        List<PurchaseHistory> purchaseHistories  = buyer.getHistory();
        List<Order> orderList = orderManager.getOrderByUser(buyer.getId());

        if (orderList.isEmpty()) {
            System.out.println(buyer.getName() + ", your purchase history is empty...\n");
            return;
        }

        //otherwise show his cart books
        System.out.println(buyer.getName() + ", your purchase history is listed below ->");

        for (Order order : orderList) {
            System.out.println("Date: " + order.getDate());
            for (Book book : order.getBooks()) {
                System.out.println("-> " + book.toString());
            }
            System.out.println();
        }
        System.out.println();
    }


    public void processPaymentRequest(User buyer) {
        List<CartItem> cartItemList = cartItemManager.getCartItemByUser(buyer.getId());

        if (cartItemList.isEmpty()) {
            System.out.println("Please add something to cart for buying!!!!\n");
            return;
        }

        double amount = 0;
        List<Book> cartItemsBook = new ArrayList<>();


        for (CartItem cartItem : cartItemList) {
            cartItemsBook.add(cartItem.getBook());
            amount += cartItem.getBook().getPrice();
        }

        Order order = new Order(buyer.getId(), cartItemsBook, amount);
        orderManager.saveOrder(order);

        // otherwise continue with payment
        paymentProcessor.setPaymentStrategy();
        paymentProcessor.processPayment(amount);

        // add or update sale to each of the seller
        updateSaleRecord(cartItemsBook);

        // empty buyers cart
        cartItemManager.removeCartItems(cartItemList);

    }



    private void updateSaleRecord(List<Book> bookList) {
        for (Book book : bookList) {
            // first get each seller from book object
            // and then update his sales
            String bookId = book.getBookId();
            String sellerId = book.getSellerId();

            SalesRecord salesRecord = salesRecordManager
                    .getSalesRecordBySellerAndBook(sellerId, bookId);

            if (salesRecord != null) {
                int timesSold = salesRecord.getTimesSold();
                salesRecord.setTimesSold(timesSold+1);
            } else {
                salesRecord = new SalesRecord(book.getSellerId(), book);
            }
            salesRecordManager.saveSalesRecord(salesRecord);
        }
    }
}
