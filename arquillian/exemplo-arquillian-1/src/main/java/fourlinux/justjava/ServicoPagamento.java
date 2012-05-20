package fourlinux.justjava;

public class ServicoPagamento {
	public Cobranca gerarCobranca(float valor, Usuario usuario) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor inválido");
		}
		return new Cobranca(usuario, valor);
	}
}
