package fourlinux.justjava;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


/**
 * Responsável por gerenciar as reservas para o cruzeiro. 
 */
public class GerenciadorReservas {
	private Map<Cabine, Usuario> reservas = new HashMap<Cabine, Usuario>();
	
	@Inject
	private ServicoPagamento pagamentos;
	
	public Cobranca reservarCabine(Cabine cabine, Usuario usuario) throws CabineReservadaException {
		if (reservas.containsKey(cabine))  {
			throw new CabineReservadaException();
		}
		
		reservas.put(cabine, usuario);
		return pagamentos.gerarCobranca(cabine.getValor(), usuario);
	}
	
	
}
