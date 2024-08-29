package PaymentModule;

public abstract class PaymentFactory {
    public abstract CreditCartPayment createCreditCartPayment();
    public abstract UpiPayment createUpiPayment();
    public abstract NetBankingPayment createNetBankingPayment();
}
