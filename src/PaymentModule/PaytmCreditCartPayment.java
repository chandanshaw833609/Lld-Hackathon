package PaymentModule;

public class PaytmCreditCartPayment extends CreditCartPayment{

    @Override
    public void processCreditCartPayment(double amount) {
        System.out.println("Payment from PAYTM credit card of "+amount + "is successfull!!");
    }
}
