package payment;

public class PaytmCreditCartPayment extends CreditCartPayment{

    @Override
    public void processCreditCartPayment(double amount) {
        System.out.println("Payment from PAYTM credit card of Rs."+ amount + " is successful...\n");
    }
}
