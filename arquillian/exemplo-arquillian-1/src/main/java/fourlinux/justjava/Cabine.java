package fourlinux.justjava;

public class Cabine {
	public enum Tipo {
		CONVENCIONAL, DUPLA, MARINE, LUXO
	}

	private String numero;
	private String navio;
	private Tipo tipo;
	private float valor;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNavio() {
		return navio;
	}

	public void setNavio(String navio) {
		this.navio = navio;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
