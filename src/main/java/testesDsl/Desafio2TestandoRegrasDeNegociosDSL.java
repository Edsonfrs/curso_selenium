package testesDsl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dsl.DSL;
import page.CampoTreinamentoPage;

public class Desafio2TestandoRegrasDeNegociosDSL {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Nome eh obrigatorio", msg);
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		page.setNome("Miguel");
		
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);

	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome("Miguel");
		page.setSobrenome("De Cervantes");
		
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Sexo eh obrigatorio", msg);

	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		page.setNome("Miguel");
		page.setSobrenome("De Cervantes");
		page.setSexoMasculino(); 
		page.setComidaCarne(); 
		page.setComidaVegetariana();
				
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoEsportes() {
		page.setNome("Miguel");
		page.setSobrenome("De Cervantes");
		page.setSexoMasculino(); 
		page.setComidaCarne(); 
		page.setEsporte("Karate", "O que eh esporte?");
		
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Voce faz esporte ou nao?", msg);

	}

}
