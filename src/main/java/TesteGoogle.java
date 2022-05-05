import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		
		//System.setProperty("webdriver.gecko.driver", "/home/edson/Temp/Drivers/geckodriver");
		//System.setProperty("webdriver.chrome.driver", "/home/edson/Temp/Drivers/chromedriver");
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(300, 200));
		driver.manage().window().setSize(new Dimension(600, 383));
		
		driver.get("https://www.google.com");
		//System.out.println(driver.getTitle());
		
		Assert.assertEquals("Google", driver.getTitle());
		
		driver.quit();
		
		
		
		
		
	}

}
