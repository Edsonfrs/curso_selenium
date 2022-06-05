import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio1CadastroComSucessoV2 {
	
	private WebDriver driver;
	
	@Before
	public void inicilaiza() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarUmCadastroComSucesso() {
				
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Edgar");

		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Allan Poe");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		//boolean resultadoSexo = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//boolean resultadoComida = driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected();
		
		WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(elementEscolaridade);
		comboEscolaridade.selectByVisibleText("Especializacao");
				
		WebElement elementEsportes = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsportes = new Select(elementEsportes);
		comboEsportes.selectByVisibleText("Karate");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
			
		Assert.assertEquals("Cadastrado!", driver.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText());
		Assert.assertEquals("Edgar", driver.findElement(By.xpath("//*[@id=\"descNome\"]/span")).getText());
		Assert.assertEquals("Allan Poe", driver.findElement(By.xpath("//*[@id=\"descSobrenome\"]/span")).getText());
		Assert.assertEquals("Masculino", driver.findElement(By.xpath("//*[@id=\"descSexo\"]/span")).getText());
		Assert.assertEquals("Carne", driver.findElement(By.xpath("//*[@id=\"descComida\"]/span")).getText());
		Assert.assertEquals("especializacao", driver.findElement(By.xpath("//*[@id=\"descEscolaridade\"]/span")).getText());
		Assert.assertEquals("Karate", driver.findElement(By.xpath("//*[@id=\"descEsportes\"]/span")).getText());

		/*
		Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).getText());
		Assert.assertEquals("Edgar", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Allan Poe", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("especializacao", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Karate", driver.findElement(By.id("descEsportes")).getText());
		*/

	}

}
