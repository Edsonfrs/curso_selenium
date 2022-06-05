import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class Desafio1CadastroComSucesso {
	
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
	public void deveRealizarUmCadastroComSucesso() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Edgar");
		String nome = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		System.out.println(nome);
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Allan Poe");
		String sobreNome = driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		boolean resultadoSexo = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		boolean resultadoComida = driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected();
		
		WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(elementEscolaridade);
		comboEscolaridade.selectByVisibleText("Especializacao");
		String selecaoEscolaridade = comboEscolaridade.getFirstSelectedOption().getText();
		
		WebElement elementEsportes = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsportes = new Select(elementEsportes);
		comboEsportes.selectByVisibleText("Karate");
		String selecaoEsportes = comboEsportes.getFirstSelectedOption().getText();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		String resultadoCadastrar = driver.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText();
		
		
		
		Assert.assertEquals("Edgar", nome);
		Assert.assertEquals("Allan Poe", sobreNome);
		Assert.assertTrue(resultadoSexo);
		Assert.assertTrue(resultadoComida);
		Assert.assertEquals("Especializacao", selecaoEscolaridade);
		Assert.assertEquals("Karate", selecaoEsportes);
		Assert.assertEquals("Cadastrado!", resultadoCadastrar);

	}

}
