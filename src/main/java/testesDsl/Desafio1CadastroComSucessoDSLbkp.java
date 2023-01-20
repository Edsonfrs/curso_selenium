package testesDsl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;

import dsl.DSL;

public class Desafio1CadastroComSucessoDSLbkp {
	
	private WebDriver driver;
	private DSL dsl;
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		dsl = new DSL(driver);
		//new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarUmCadastroComSucesso() {
		
		dsl.escrever("elementosForm:nome", "Edgar");
		dsl.escrever("elementosForm:sobrenome", "Allan Poe");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:escolaridade", "Especializacao" );
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		
		dsl.clicarBotao("elementosForm:cadastrar");
			
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Edgar"));
		Assert.assertEquals("Sobrenome: Allan Poe", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: especializacao", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Karate", dsl.obterTexto("descEsportes"));

	}


}
