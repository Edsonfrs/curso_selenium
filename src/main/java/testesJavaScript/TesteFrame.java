package testesJavaScript;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dsl.DSL;


public class TesteFrame {
	
	private WebDriver driver;
	private DSL dsl;
	
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		dsl = new DSL(driver);
		
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveInteragirComFrames() {
		
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		System.out.println(msg);
		Assert.assertEquals("Frame OK!", msg);
		
		
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);

	}

	@Test
	public void deveInteragirComFrameEscondido() {
		
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();

		Assert.assertEquals("Frame OK!", msg);

	}

}
