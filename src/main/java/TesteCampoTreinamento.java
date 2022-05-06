import java.util.List;

import org.junit.Assert;
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
	public void deveVerificarvaloresCombo() {
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
	

}
