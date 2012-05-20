package fourlinux.justjava;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import fourlinux.justjava.Cabine.Tipo;

@RunWith(Arquillian.class)
public class GerenciadorReservasTest {
	@Inject
	private GerenciadorReservas reservas;

	@Deployment
	public static JavaArchive gerandoArquivoDeploy() {
		JavaArchive arquivo = ShrinkWrap.create(JavaArchive.class);
		arquivo.addClasses(
				Cabine.class, CabineReservadaException.class, Cobranca.class,
				GerenciadorReservas.class, ServicoPagamento.class,
				Usuario.class);
		arquivo.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
		// Ou
		// ShrinkWrap.create(JavaArchive.class).addPackages(true, fourlinux.justjava);
		
		return arquivo;
	}
	
	@Test
	public void reservaDeveGerarCobranca() throws Exception {
		Cabine cabine = new Cabine();
		cabine.setNavio("Titanic");
		cabine.setNumero("12-A");
		cabine.setTipo(Tipo.LUXO);
		cabine.setValor(340.00f);
		
		Usuario usuario = new Usuario("gabriel.ozeas1@gmail.com", "Gabriel Ozeas");
		
		Cobranca cobranca = reservas.reservarCabine(cabine, usuario);
		assertEquals(new Float(340), new Float(cobranca.getValor()));
	}
}
