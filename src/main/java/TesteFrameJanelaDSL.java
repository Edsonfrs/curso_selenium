import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrameJanelaDSL {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
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
		
		Assert.assertEquals("Frame OK!", msg);
		
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", "Robert");

	}
	
	@Test
	public void deveInteragirComJanela() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "... e agora?");

	}
	
	@Test
	public void deveInteragirComJanelaSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textArea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textArea"), "... e agora?");

	}
	

}
