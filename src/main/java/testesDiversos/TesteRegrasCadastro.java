package testesDiversos;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dsl.DSL;
import page.CampoTreinamentoPage;

public class TesteRegrasCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	private String nome;
	private String sobrenome;
	private String sexo;
	private List<String> comidas;
	private String [] esportes;
	private String msg;
	
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(600, 334));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/camp/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveValidarRegras() {
	page.setNome(nome);
	page.setSobrenome(sobrenome);
	
	if (sexo.equals("Masculino")) {
		page.setSexoMasculino();
	}
	else {
		page.setSexoFeminino();
	}
	
	if (comidas.contains("Carne")) page.setComidaCarne();
	if (comidas.contains("Frango")) page.setComidaFrango();
	if (comidas.contains("Pizza")) page.setComidaPizza();
	if (comidas.contains("Vegetariana")) page.setComidaVegetariana();

	page.setComidaCarne(); 
	page.setEsporte(esportes);
	
	page.cadastrar();
		
	Assert.assertEquals(msg , dsl.alertaObterTextoEAceita());

}

}
