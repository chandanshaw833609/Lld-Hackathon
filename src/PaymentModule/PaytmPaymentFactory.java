package PaymentModule;

public class PaytmPaymentFactory extends PaymentFactory{
    @Override
    public CreditCartPayment createCreditCartPayment() {
        return new PaytmCreditCartPayment();
    }

    @Override
    public UpiPayment createUpiPayment() {
        return new PaytmUpiPayment();
    }

    @Override
    public NetBankingPayment createNetBankingPayment() {
        return new PaytmNetBankingPayment();
    }
}
