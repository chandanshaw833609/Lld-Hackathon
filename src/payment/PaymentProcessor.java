package payment;

import java.util.Scanner;

public class PaymentProcessor {
    private PaymentFactory paymentStrategy;


    public void setPaymentStrategy() {
        System.out.println("Choose Payment Method : ");
        System.out.println("For GPay enter -> 1");
        System.out.println("For Paytm enter -> 2");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

            switch (input) {
                case "1" -> paymentStrategy = new GPayPaymentFactory();
                case "2" -> paymentStrategy = new PaytmPaymentFactory();
                default -> {
                    System.out.println("Choose correct payment method...\n");
                    setPaymentStrategy();
                }
            }
    }

    public void processPayment(double amount) {
            System.out.println("For UPI Payment enter -> 1");
            System.out.println("For CreditCard Payment enter -> 2");
            System.out.println("For NetBanking Payment enter -> 3");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    UpiPayment upiPayment = paymentStrategy.createUpiPayment();
                    upiPayment.processUpiPayment(amount);
                }
                case "2" -> {
                    CreditCartPayment creditCartPayment = paymentStrategy.createCreditCartPayment();
                    creditCartPayment.processCreditCartPayment(amount);
                }
                case "3" -> {
                    NetBankingPayment netBankingPayment = paymentStrategy.createNetBankingPayment();
                    netBankingPayment.processNetBankingPayment(amount);
                }
                default -> {
                    System.out.println("Select a valid payment method...\n");
                    processPayment(amount);
                }
            }
        }

}
