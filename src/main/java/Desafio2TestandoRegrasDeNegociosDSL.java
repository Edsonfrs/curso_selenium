import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio2TestandoRegrasDeNegociosDSL {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Nome eh obrigatorio", msg);
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		dsl.escrever("elementosForm:nome", "Edgar");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);

	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escrever("elementosForm:nome", "Edgar");
		dsl.escrever("elementosForm:sobrenome", "Allan Poe");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Sexo eh obrigatorio", msg);

	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		dsl.escrever("elementosForm:nome", "Edgar");
		dsl.escrever("elementosForm:sobrenome", "Allan Poe");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoEsportes() {
		dsl.escrever("elementosForm:nome", "Edgar");
		dsl.escrever("elementosForm:sobrenome", "Allan Poe");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Voce faz esporte ou nao?", msg);

	}

}
