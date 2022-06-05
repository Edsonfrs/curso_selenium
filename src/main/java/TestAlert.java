import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAlert {
	
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
	public void deveInteragirComAlertSimples() {

		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String mensagem = alert.getText();	
		
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(mensagem);

	}
	
	@Test
	public void deveInteragirComAlertConfirmOk() {

		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
			
		alert.accept();
		String mensagem = alert.getText();

		Assert.assertEquals("Confirmado", mensagem);

	}
	
	@Test
	public void deveInteragirComAlertConfirmCancel() {

		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();

		alert.dismiss();
		String mensagem = alert.getText();
		
		Assert.assertEquals("Negado", mensagem);

	}
	
	@Test
	public void deveInteragirComPromptOk() {
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("23");
		alert.accept();
		
		Assert.assertEquals("Era 23?", alert.getText());
		alert.accept();		
		
		Assert.assertEquals(":D", alert.getText());

	}
	
	@Test
	public void deveInteragirComPromptCancel() {
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("28");
		alert.dismiss();
		
		Assert.assertEquals("Era null?", alert.getText());
		alert.dismiss();		
		
		Assert.assertEquals(":(", alert.getText());

	}


}
