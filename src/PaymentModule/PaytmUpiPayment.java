package PaymentModule;

public class PaytmUpiPayment extends UpiPayment{
    @Override
    public void processUpiPayment(double amount) {
        System.out.println("Payment from PAYTM upi of Rs."+amount + " is successful...\n");
    }
}
