package foulinux.justjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import fourlinux.justjava.CreditCardInfo;
import fourlinux.justjava.CreditCardInfo.CreditCardType;
import fourlinux.justjava.CreditCardPaymentService;

public class CreditCardPaymentServiceTest {
	@Test
	public void chargeRequestWithoutType() throws Exception {
		CreditCardInfo ccInfo = new CreditCardInfo();
		// ccInfo.setType(CreditCardType.MASTERCARD);
		ccInfo.setCardholderName("GABRIEL OZEAS");
		ccInfo.setExpirationDate("05/2017");
		ccInfo.setNumber("5345123654123");
		
		CreditCardPaymentService service = new CreditCardPaymentService();
		try {
			service.charge(50.20, ccInfo);
			fail("Service need to throw a IllegalArgumentException");
		} catch (IllegalArgumentException iae) {
			assertEquals("Your credit card type was not specified.", iae.getMessage());
		}
	}
}
