package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testesDsl.Desafio1CadastroComSucessoDSL;
import testesDsl.TesteCampoTreinamentoDSL;
import testesParametrizaveis.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteRegrasCadastro.class,
	TesteCampoTreinamentoDSL.class, 
	Desafio1CadastroComSucessoDSL.class

})
public class SuiteTestes {
	
	

}
