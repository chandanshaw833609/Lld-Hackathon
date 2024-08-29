package PaymentModule;

public class GPayPaymentFactory extends PaymentFactory{
    @Override
    public CreditCartPayment createCreditCartPayment() {
        return new GPayCreditCartPayment();
    }

    @Override
    public UpiPayment createUpiPayment() {
        return new GPayUpiPayment();
    }

    @Override
    public NetBankingPayment createNetBankingPayment() {
        return new GPayNetBankingPayment();
    }
}
