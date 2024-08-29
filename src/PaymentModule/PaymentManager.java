package PaymentModule;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class PaymentManager {
    private static PaymentManager paymentManager;
    private PaymentFactory paymentMethod;

    private final Scanner scanner;

    private PaymentManager() {
        scanner = new Scanner(System.in);
    }

    public void initialisePayment(double amount) {
        System.out.println("Choose Payment Solution : ");
        System.out.println("For GPay enter 1 : ");
        System.out.println("For Paytm enter 2 : ");
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> paymentMethod = new GPayPaymentFactory();
            case "2" -> paymentMethod = new PaytmPaymentFactory();
            default -> {
                System.out.println("Choose correct input");
            }
        }

        System.out.println("For UPI enter 1 : ");
        System.out.println("For CreditCart enter 2");
        System.out.println("For NetBanking enter 3");
        input = scanner.nextLine();

        CreditCartPayment creditCartPayment;
        UpiPayment upiPayment;
        NetBankingPayment netBankingPayment;

        switch (input) {
            case "1" -> {
                upiPayment = paymentMethod.createUpiPayment();
                upiPayment.processUpiPayment(amount);
            }
            case "2" -> {
                creditCartPayment = paymentMethod.createCreditCartPayment();
                creditCartPayment.processCreditCartPayment(amount);
            }
            case "3" -> {
                netBankingPayment = paymentMethod.createNetBankingPayment();
                netBankingPayment.processNetBankingPayment(amount);
            }
            default -> System.out.println("Select a valid payment method");
        }

    }

    public void selectPaymentMethod() {

    }
}
