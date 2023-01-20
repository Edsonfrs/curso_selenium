package testesDiversos;
//import org.junit.Assert;
import org.junit.Test;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteJanela {
	
	@Test
	public void deveInteragirComJanelaFormaFacil() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(700, 434));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Tudo ok?");
		driver.close();
		
		driver.switchTo().window("");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Tudo ok? Again...");

		driver.quit();
	}
	
	@Test
	public void deveInteragirComJanelaFormaGenerica() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(700, 434));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		//System.out.println(driver.getWindowHandle());  <-- Janela Principal
		//System.out.println(driver.getWindowHandles());  <-- Janelas abertas
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("All Right???");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("All Right??? Again...");
		
		driver.quit();
	}

}
