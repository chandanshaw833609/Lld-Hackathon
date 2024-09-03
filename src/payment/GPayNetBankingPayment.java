package payment;

public class GPayNetBankingPayment extends NetBankingPayment{
    @Override
    public void processNetBankingPayment(double amount) {
        System.out.println("Payment from GPay net-banking of Rs."+amount + " is successful...\n");
    }
}
