import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

	

}
