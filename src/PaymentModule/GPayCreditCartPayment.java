package PaymentModule;

public class GPayCreditCartPayment extends CreditCartPayment{
    @Override
    public void processCreditCartPayment(double amount) {
        System.out.println("Payment from GPay credit card of Rs."+amount + " is successful...\n");
    }
}
