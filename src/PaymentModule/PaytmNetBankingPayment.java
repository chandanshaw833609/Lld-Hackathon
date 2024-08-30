package PaymentModule;

public class PaytmNetBankingPayment extends NetBankingPayment{
    @Override
    public void processNetBankingPayment(double amount) {
        System.out.println("Payment from PAYTM netbanking of "+amount + "is successfull!!");
    }
}
