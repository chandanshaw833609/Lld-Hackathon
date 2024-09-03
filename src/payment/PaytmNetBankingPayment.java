package payment;

public class PaytmNetBankingPayment extends NetBankingPayment{
    @Override
    public void processNetBankingPayment(double amount) {
        System.out.println("Payment from PAYTM net-banking of Rs."+amount + " is successful...\n");
    }
}
