package poc;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class POC_01 {

	WebDriver driver = null;
	String url = "https://github.com/bonigarcia/webdrivermanager-examples";
	String titleEsperado = "GitHub - bonigarcia/webdrivermanager-examples: JUnit 4 tests with Selenium and WebDriverManager";

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void Teste01() throws InterruptedException {
		driver.get(url);
		assertEquals(driver.getTitle(), titleEsperado);
	}

}
