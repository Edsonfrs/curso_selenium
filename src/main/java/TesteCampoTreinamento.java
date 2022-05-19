import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	@Test
	public void deveInteragirComTestField() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		String texto_encontrado = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		
		Assert.assertEquals("Teste de escrita", texto_encontrado);

		driver.quit();
	}
	
	@Test	
	public void deveInteragirComTestArea() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste do campo sugestões");
		String sugestao = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
		
		Assert.assertEquals("Teste do campo sugestões", sugestao);
				
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		boolean resultado = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
		
		Assert.assertTrue(resultado);
				
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		boolean resultado = driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected();
		
		Assert.assertTrue(resultado);
				
		driver.quit();
	} 
	
	@Test
	public void deveInteragirComCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// Possibilidades de seleção de elementos no elemento Combo
		//combo.selectByIndex(5);
		//combo.selectByValue("mestrado");
		combo.selectByVisibleText("Superior");
		String selecao = combo.getFirstSelectedOption().getText();
		
		Assert.assertEquals("Superior", selecao);
				
		driver.quit();
	}

	@Test
	public void deveVerificarValoresCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// Pegando a lista de opções do Combo
		List<WebElement> options = combo.getOptions();
		
		// Pegando a quantidade opções no Combo
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		
		Assert.assertTrue(encontrou);
				
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresDoComboMultiplo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
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
		
		driver.quit();

	}
	
	@Test
	public void deveInteragirComBotoes() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("buttonSimple")).click();
		String texto = driver.findElement(By.id("buttonSimple")).getAttribute("value");

		Assert.assertEquals("Obrigado!", texto);
		
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComLink() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		String resultado = driver.findElement(By.id("resultado")).getText();
		
		Assert.assertEquals("Voltou!", resultado);
		//Assert.fail();
		
		driver.quit();
		
	}
	
	@Test
	public void deveBuscarTextosNaPaginaFormaUm() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		String pagina = driver.findElement(By.tagName("body")).getText(); 
		
		Assert.assertTrue(pagina.contains("Campo de Treinamento"));
		
		
		driver.quit();
		
	}
	
	@Test
	public void deveBuscarTextosNaPaginaFormaDois() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		String pagina = driver.findElement(By.tagName("h3")).getText(); 
		String pagina2 = driver.findElement(By.className("facilAchar")).getText();
		
		Assert.assertEquals("Campo de Treinamento", pagina);
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", pagina2);
		
		
		driver.quit();
	}

}
