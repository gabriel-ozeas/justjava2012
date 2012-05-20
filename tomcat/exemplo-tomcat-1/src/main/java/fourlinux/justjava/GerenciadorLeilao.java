package fourlinux.justjava;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class GerenciadorLeilao {
	private Map<String, Lance> lances = new HashMap<String, Lance>();
	private Map<String, List<Lance>> historico = new HashMap<String, List<Lance>>();
	
	public List<Lance> getHistorico(String itemId) {
		return historico.get(itemId);
	}
	
	public Lance finalizarLeilao(String itemId) {
		Lance lance = lances.remove(itemId);
		historico.remove(itemId);
		return lance;
	}
	
	public void darLance(String itemId, String email, float valor) {
		if (!lances.containsKey(itemId)) {
			throw new IllegalArgumentException("Item não encontrado!");
		}
		
		if (valor > lances.get(itemId).getValorLance()) {
			Lance lance = new Lance(email, itemId, valor, new Date());
			lances.put(itemId, lance);
			historico.get(itemId).add(lance);
		}
	}
	
	public boolean criarLeilao(String itemId) {
		if (!lances.containsKey(itemId)) {
			lances.put(itemId, null);
			historico.put(itemId, new ArrayList<Lance>());
			return true;
		} else {
			return false;
		}
	}
}
