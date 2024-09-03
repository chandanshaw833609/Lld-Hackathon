package payment;

public class GPayUpiPayment extends UpiPayment{
    @Override
    public void processUpiPayment(double amount) {
        System.out.println("Payment from GPay upi of Rs."+amount + " is successful...\n");
    }
}
