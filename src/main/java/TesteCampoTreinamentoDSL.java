import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamentoDSL {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa(){
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
	public void deveInteragirComTestField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		String texto_encontrado = dsl.obterValorCampo("elementosForm:nome");
		
		Assert.assertEquals("Teste de escrita", texto_encontrado);
	}
	
	@Test	
	public void deveInteragirComTestArea() {
		dsl.escrever("elementosForm:sugestoes", "Teste do campo sugestões");
		String sugestao = dsl.obterValorCampo("elementosForm:sugestoes");
		
		Assert.assertEquals("Teste do campo sugestões", sugestao);
	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Edson");
		String nome = dsl.obterValorCampo("elementosForm:nome");
		dsl.escrever("elementosForm:sobrenome", "F de Souza");
		String sobrenome = dsl.obterValorCampo("elementosForm:sobrenome");
		
		Assert.assertEquals("Edson", nome);
		Assert.assertEquals("F de Souza", sobrenome);
		
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		boolean resultado = dsl.isRadioMarcado("elementosForm:sexo:0");
		
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		boolean resultado = dsl.isCheckMarcado("elementosForm:comidaFavorita:0");
		
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		String selecao = dsl.obterValorCombo("elementosForm:escolaridade");
		
		Assert.assertEquals("Superior", selecao);
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		int quantOpcoes = dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade");
		
		Assert.assertEquals(8, quantOpcoes);
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));

	}
	
	@Test
	public void deveVerificarValoresDoComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
				
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
		
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		String texto = dsl.obterValueElemento("buttonSimple");

		Assert.assertEquals("Obrigado!", texto);
		
	}
	
	@Test
	public void deveInteragirComLink() {
		dsl.clicarLink("Voltar");
		String resultado = dsl.obterTexto("resultado");
		
		Assert.assertEquals("Voltou!", resultado);
		
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		String textoForma1 = dsl.obterTexto(By.tagName("h3"));
		String textoForma2 = dsl.obterTexto(By.className("facilAchar"));
		
		Assert.assertEquals("Campo de Treinamento", textoForma1);
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", textoForma2);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
