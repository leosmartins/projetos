package poc;

import static msp.Enumeradores.navegadores.CHROME;
import static msp.WebApps.webAppDriver;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POC_01 {

	WebDriver driver = null;
	String url = "https://github.com/bonigarcia/webdrivermanager-examples";
	String titleEsperado = "GitHub - bonigarcia/webdrivermanager-examples: JUnit 4 tests with Selenium and WebDriverManager";

	@BeforeClass
	public void setUp() {
		driver = webAppDriver(CHROME, 30);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(description = "Teste de navegação e asset usando WebDriverManager")
	public void Teste01() throws InterruptedException {
		driver.get(url);
		assertEquals(driver.getTitle(), titleEsperado);
	}

}
