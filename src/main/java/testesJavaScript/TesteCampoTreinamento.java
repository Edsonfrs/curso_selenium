package testesJavaScript;

//import java.sql.Driver;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveInteragirComTestField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		String texto_encontrado = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

		Assert.assertEquals("Teste de escrita", texto_encontrado);

	}

	@Test
	public void deveInteragirComTestArea() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste do campo sugestões");
		String sugestao = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");

		Assert.assertEquals("Teste do campo sugestões", sugestao);

	}

	@Test
	public void deveInteragirComRadioButton() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		boolean resultado = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();

		Assert.assertTrue(resultado);

	}

	@Test
	public void deveInteragirComCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		boolean resultado = driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected();

		Assert.assertTrue(resultado);

	}

	@Test
	public void deveInteragirComCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// Possibilidades de seleção de elementos no elemento Combo
		// combo.selectByIndex(5);
		// combo.selectByValue("mestrado");
		combo.selectByVisibleText("Superior");
		String selecao = combo.getFirstSelectedOption().getText();

		Assert.assertEquals("Superior", selecao);

	}

	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// Pegando a lista de opções do Combo
		List<WebElement> options = combo.getOptions();

		// Pegando a quantidade opções no Combo
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}

		Assert.assertTrue(encontrou);

	}

	@Test
	public void deveVerificarValoresDoComboMultiplo() {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectOptions.size());

	}

	@Test
	public void deveInteragirComBotoes() {
		driver.findElement(By.id("buttonSimple")).click();
		String texto = driver.findElement(By.id("buttonSimple")).getAttribute("value");

		Assert.assertEquals("Obrigado!", texto);

	}

	@Test
	public void deveInteragirComLink() {
		driver.findElement(By.linkText("Voltar")).click();
		String resultado = driver.findElement(By.id("resultado")).getText();

		Assert.assertEquals("Voltou!", resultado);
		// Assert.fail();

	}

	@Test
	public void deveBuscarTextosNaPaginaFormaUm() {
		String pagina = driver.findElement(By.tagName("body")).getText();

		Assert.assertTrue(pagina.contains("Campo de Treinamento"));

	}

	@Test
	public void deveBuscarTextosNaPaginaFormaDois() {
		String pagina = driver.findElement(By.tagName("h3")).getText();
		String pagina2 = driver.findElement(By.className("facilAchar")).getText();

		Assert.assertEquals("Campo de Treinamento", pagina);
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", pagina2);

	}

	@Test
	public void testeJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando JS via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value= 'Escrito via JS' ");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type= 'radio' ");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
	}

}
