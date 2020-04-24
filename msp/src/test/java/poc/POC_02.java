package poc;

import static msp.Enumeradores.navegadores.*;
import static msp.Enumeradores.locators.*;
import static msp.WebApps.webAppDriver;
import static msp.FuncoesBasicas.*;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POC_02 {

	WebDriver driver = null;
	String url = "https://www.google.com.br/";
	String linkPesquisa = "//div[@id='rso']/div/div/div/div/a/h3";
	String validar = "SeleniumHQ Browser Automation";

	@BeforeClass
	public void setUp() {
		driver = webAppDriver(CHROME, 30);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, description = "Navegar para o Google")
	public void irPara() {
		navegar(driver, url, 2);
	}

	@Test(priority = 2, description = "Pesquisar por um termo")
	public void pesquisar() {

		// Digitar e Pesquisar
		digitar(driver, NAME, "q", "SeleniumHQ", 2);
		clicar(driver, NAME, "btnK", 2);
		
		// Clicar no link
		clicar(driver, XPATH, linkPesquisa, 2);
	}
	
	@Test(priority = 3, description = "Validação da pesquisa")
	public void validar() {
		
		// Validar o titulo obtido com o esperado
		assertEquals(driver.getTitle(), validar);
	}

}