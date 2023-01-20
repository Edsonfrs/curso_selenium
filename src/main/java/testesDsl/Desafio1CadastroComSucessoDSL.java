package testesDsl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.CampoTreinamentoPage;

public class Desafio1CadastroComSucessoDSL {
	
	private WebDriver driver;
	//private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		//dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarUmCadastroComSucesso() {
		
		page.setNome("Howard");
		page.setSobrenome("P. Lovecraft");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("Especializacao");
		page.setEsporte("Karate");
		
		page.cadastrar();
					
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Howard"));
		Assert.assertEquals("Sobrenome: P. Lovecraft", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Carne", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: especializacao", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Karate", page.obterEsporteCadastro());

	}


}
