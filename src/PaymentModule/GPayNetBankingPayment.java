package PaymentModule;

public class GPayNetBankingPayment extends NetBankingPayment{
    @Override
    public void processNetBankingPayment(double amount) {
        System.out.println("Payment from GPay Netbanking amount of rs "+amount + "is successfull!!");
    }
}
