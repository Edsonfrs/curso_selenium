import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAlert {
	
	@Test
	public void deveInteragirComAlertSimples() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String mensagem = alert.getText();	
		
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(mensagem);
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirmOk() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
			
		alert.accept();
		String mensagem = alert.getText();

		Assert.assertEquals("Confirmado", mensagem);
				
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirmCancel() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();

		alert.dismiss();
		String mensagem = alert.getText();
		
		Assert.assertEquals("Negado", mensagem);
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComPromptOk() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("23");
		alert.accept();
		
		Assert.assertEquals("Era 23?", alert.getText());
		alert.accept();		
		
		Assert.assertEquals(":D", alert.getText());

		driver.quit();
	}
	
	@Test
	public void deveInteragirComPromptCancel() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("28");
		alert.dismiss();
		
		Assert.assertEquals("Era null?", alert.getText());
		alert.dismiss();		
		
		Assert.assertEquals(":(", alert.getText());

		driver.quit();
	}


}
