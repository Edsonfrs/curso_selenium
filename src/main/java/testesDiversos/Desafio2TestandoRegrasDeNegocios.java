package testesDiversos;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio2TestandoRegrasDeNegocios {
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveTestarRegraDoCampoNome() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();	
		
		Assert.assertEquals("Nome eh obrigatorio", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoSobreNome() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Washington");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();	
		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoSexo() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Arthur");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Conan Doyle");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();	
		
		Assert.assertEquals("Sexo eh obrigatorio", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoComidaFavorita() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Arthur");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Conan Doyle");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();	
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);

	}
	
	@Test
	public void deveTestarRegraDoCampoEsportes() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Arthur");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Conan Doyle");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();	
		
		Assert.assertEquals("Voce faz esporte ou nao?", msg);

	}

}
