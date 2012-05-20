package fourlinux.justjava;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.startup.Tomcat;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyBuilder;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class CarrinhoComprasServletIT {
	private Tomcat servidor;
	
	
	@Before
	public void configurandoAplicacao() throws Exception {
		// Path do arquivo que será criado
		File arquivo = new File("target/carrinho.war");
		
		// Referencia para uma arquivo WAR
		WebArchive carrinho = ShrinkWrap.create(WebArchive.class);
		// Adiciona todas as classes dentro do pacote recursivamente
		carrinho.addPackages(true, "fourlinux.justjava.carrinho");
		
		// Adicionando dependencias do Maven		
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class);
		MavenDependencyBuilder dominio = resolver.artifact("br.com.4linux:exemplo-shrinkwrap-dominio:1.0");
		
		carrinho.addAsLibraries(dominio.resolveAs(GenericArchive.class));
		
		// Exporta o conteudo em formato zip (.war), sobrescrevendo algum que já estiver lá.
		carrinho.as(ZipExporter.class).exportTo(arquivo.getAbsoluteFile(), true);
		
		// Configura container de servlet embutido
		servidor = new Tomcat();
		// Aponta o diretorio temporario para que o container necessita
		servidor.setBaseDir("target/temp");
		// Especifica a porta onde irá funcionar
		servidor.setPort(8081);
		// Cria um contexto /carrinho e o .war com esse contexto.
		servidor.addWebapp("/carrinho", arquivo.getAbsolutePath());
		// Inicia servidor
		servidor.start();
	}
	
	@Test
	public void verificandoTotalItensInicial() throws Exception {
		WebDriver navegador = new HtmlUnitDriver();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		navegador.get("http://localhost:8081/carrinho/compras");
		WebElement body = navegador.findElement(By.tagName("body"));
		assertEquals("Listando Carrinho de Compras: 0 itens", body.getText());
	}
	
	@After
	public void desligarServidor() throws Exception {
		servidor.stop();
	}
	
	public static void main(String[] args) throws Exception {
		new CarrinhoComprasServletIT().configurandoAplicacao();
	}
}
