package fourlinux.justjava;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import fourlinux.justjava.LeilaoServlet;

public class LeilaoServletIT {
	private Tomcat servidor;
	
	@Before
	public void configurandoAplicacao() throws Exception {
		// Configura container de servlet embutido
		servidor = new Tomcat();
		// Aponta o diretorio temporario para que o container necessita
		servidor.setBaseDir("target/temp");
		// Especifica a porta onde irá funcionar
		servidor.setPort(8081);
		
		File diretorioWebapp = new File("src/main/webapp");
		Context contexto = servidor.addWebapp("/", diretorioWebapp.getAbsolutePath());
		
		servidor.addServlet("/", "LeilaoServlet", new LeilaoServlet());
		contexto.addServletMapping("/leilao", "LeilaoServlet");
		
		// Inicia servidor
		servidor.start();
	}
	
	@Test
	public void verificandoTotalItensInicial() throws Exception {
		WebDriver navegador = new HtmlUnitDriver();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		navegador.get("http://localhost:8081/leilao");
		WebElement body = navegador.findElement(By.tagName("h1"));
		assertEquals("Histórico do item", body.getText());
	}
	
	@After
	public void desligarServidor() throws Exception {
		servidor.stop();
	}
	
	public static void main(String[] args) throws Exception {
		new LeilaoServletIT().configurandoAplicacao();
	}
}
