package fourlinux.justjava;

import java.util.Date;

public class Lance {
	String emailUsuario;
	String itemId;
	float valorLance;
	Date data;

	public Lance() {
	}

	public Lance(String emailUsuario, String itemId, float valorLance,
			Date data) {
		this.emailUsuario = emailUsuario;
		this.itemId = itemId;
		this.valorLance = valorLance;
		this.data = data;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public float getValorLance() {
		return valorLance;
	}

	public void setValorLance(float valorLance) {
		this.valorLance = valorLance;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
