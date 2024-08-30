package PaymentModule;

public class GPayUpiPayment extends UpiPayment{
    @Override
    public void processUpiPayment(double amount) {
        System.out.println("Payment from GPay upi payment of "+amount + "is successfull!!");
    }
}
