package fourlinux.justjava;

public class CreditCardInfo {
	public enum CreditCardType {
		MASTERCARD, VISA, AMEX
	}

	private CreditCardType type;
	private String number;
	private String expirationDate;
	private String cardholderName;
	
	public CreditCardInfo() {}
	public CreditCardInfo(CreditCardType type, String number,
			String expirationDate, String cardholderName) {
		super();
		this.type = type;
		this.number = number;
		this.expirationDate = expirationDate;
		this.cardholderName = cardholderName;
	}

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

}
