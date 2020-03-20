package msp;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.security.InvalidParameterException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebApps {

	public static WebDriver webAppDriver(Enumeradores.navegadores navegador, int timeOut) {

		switch (navegador) {

		case CHROME: {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(timeOut, SECONDS);
			return driver;
		}
		case FIREFOX: {
			WebDriverManager.firefoxdriver().setup();
			WebDriver  driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(timeOut, SECONDS);
			return driver;
		}
		default:
			throw new InvalidParameterException("Navegador Inválido!");
		}
	}
}