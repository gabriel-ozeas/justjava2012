package fourlinux.justjava;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

public class CarrinhoComprasServletIT {
	@Test
	public void adicionandoItemNoCarrinho() throws Exception {
		// Path do arquivo que será criado
		File arquivo = new File("target/carrinho.war");
		
		// Referencia para uma arquivo WAR
		WebArchive carrinho = ShrinkWrap.create(WebArchive.class);
		// Adiciona todas as classes dentro do pacote recursivamente
		carrinho.addPackages(true, "fourlinux.justjava.carrinho");
		
		// Exporta o conteudo em formato zip (.war), sobrescrevendo algum que já estiver lá.
		carrinho.as(ZipExporter.class).exportTo(arquivo.getAbsoluteFile(), true);
		
	}
}
