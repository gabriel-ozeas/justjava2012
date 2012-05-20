package fourlinux.justjava;

public class CreditCardPaymentService {

	public void charge(double value, CreditCardInfo ccInfo) {
		if (ccInfo.getType() == null) {
			throw new IllegalArgumentException("Your credit card type was not specified.");
		}
	}

}
