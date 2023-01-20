package testesParametrizaveis;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dsl.DSL;
import page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	// Técnica Data Driven Test - Teste Orientado a Dados
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msgParametrizada;
	
	
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
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio" },
			{"Algernon", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio" },
			{"Algernon", "Blackwood", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio" },
			{"Algernon", "Blackwood", "Masculino", Arrays.asList("Carne", "Vegetariana"), new String[]{}, "Tem certeza que voce eh vegetariano?" },
			{"Algernon", "Blackwood", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?" }
			
		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) 
		{
			page.setSexoMasculino(); 
		}
		
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		
		if (comidas.contains("Carne")) page.setComidaCarne();
		if (comidas.contains("Frango")) page.setComidaFrango();
		if (comidas.contains("Pizza")) page.setComidaPizza();
		if (comidas.contains("Vegetariana")) page.setComidaVegetariana();

		page.setEsporte(esportes);
		
		page.cadastrar();
		String msg = dsl.alertaObterTextoEAceita();
		
		System.out.println(msgParametrizada);
		
		Assert.assertEquals(msgParametrizada, msg);

	}

}
