package fourlinux.justjava.dominio;

public class Cobranca {
	private Usuario usuario;
	private float valor;
	
	public Cobranca(Usuario usuario, float valor) {
		super();
		this.usuario = usuario;
		this.valor = valor;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
