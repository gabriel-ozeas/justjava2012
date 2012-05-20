package fourlinux.justjava.negocio;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import fourlinux.justjava.dominio.Cabine;
import fourlinux.justjava.dominio.Cobranca;
import fourlinux.justjava.dominio.Usuario;


/**
 * Responsável por gerenciar as reservas para o cruzeiro. 
 */
@Singleton
public class GerenciadorReservas {
	private Map<Cabine, Usuario> reservas = new HashMap<Cabine, Usuario>();
	
	@Inject
	private ServicoPagamento pagamentos;
	
	public Cobranca reservarCabine(Cabine[] cabines, Usuario usuario) throws CabineReservadaException {
		float totalDiaria = 0.0f;
		for (Cabine cabine : cabines) {
			if (reservas.containsKey(cabine))  {
				throw new CabineReservadaException();
			}
			reservas.put(cabine, usuario);
			totalDiaria += cabine.getDiaria();
		}
		return pagamentos.gerarCobranca(totalDiaria, usuario);
	}
	
	public int totalReservasFeitas(String navio) {
		int total = 0;
		for (Cabine cabine : reservas.keySet()) {
			if (cabine.getNavio().equals(navio)) {
				total++;
			}
		}
		return total;
	}
}
