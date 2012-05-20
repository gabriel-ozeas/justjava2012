package fourlinux.justjava.dominio;

public class Cabine {
	public enum Tipo {
		CONVENCIONAL, DUPLA, MARINE, LUXO
	}

	private String numero;
	private String navio;
	private Tipo tipo;
	private float diaria;

	public Cabine() {}
	public Cabine(String numero, String navio, Tipo tipo, float diaria) {
		this.numero = numero;
		this.navio = navio;
		this.tipo = tipo;
		this.diaria = diaria;
	}

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

	public float getDiaria() {
		return diaria;
	}

	public void setDiaria(float diaria) {
		this.diaria = diaria;
	}
}
