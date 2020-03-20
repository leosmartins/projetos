package msp;

import static msp.Enumeradores.locators.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.android.AndroidElement;

public abstract class FuncoesBasicas<T extends WebElement> {

	public static void verificaSleep(int segundos) {
		if (segundos > 0) {
			try {
				Thread.sleep(segundos * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void highLight(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}

	@SuppressWarnings("unchecked")
	public static <T extends WebElement> T elemento(WebDriver driver, Enumeradores.locators locator,
			String valorLocator) {
		T element;

		if (XPATH.equals(locator)) {
			element = (T) driver.findElement(By.xpath(valorLocator));
		} else if (ID.equals(locator)) {
			element = (T) driver.findElement(By.id(valorLocator));

		} else if (NAME.equals(locator)) {
			element = (T) driver.findElement(By.name(valorLocator));

		} else if (CSS.equals(locator)) {
			element = (T) driver.findElement(By.cssSelector(valorLocator));

		} else if (LINK.equals(locator)) {
			element = (T) driver.findElement(By.linkText(valorLocator));

		} else if (CLASSE.equals(locator)) {
			element = (T) driver.findElement(By.className(valorLocator));

		} else {
			throw new InvalidSelectorException("O locator utilizado não existe!");
		}

		return element;
	}

	public static <T extends WebElement> T digitar(WebDriver driver, Enumeradores.locators locator, String valorLocator,
			String texto) {
		return digitar(driver, locator, valorLocator, texto, 0);
	}

	public static <T extends WebElement> T digitar(WebDriver driver, Enumeradores.locators locator, String valorLocator,
			String texto, int segundos) {

		T element;

		element = elemento(driver, locator, valorLocator);

		if (element instanceof AndroidElement) {
			element.click();
			element.clear();
			((AndroidElement) element).setValue(texto);
		} else {
			highLight(driver, element);
			element.clear();
			element.sendKeys(texto);
		}

		verificaSleep(segundos);

		return element;
	}

	public static <T extends WebElement> T clicar(WebDriver driver, Enumeradores.locators locator,
			String valorLocator) {
		return clicar(driver, locator, valorLocator, 0);
	}

	public static <T extends WebElement> T clicar(WebDriver driver, Enumeradores.locators locator, String valorLocator,
			int segundos) {

		T element;

		element = elemento(driver, locator, valorLocator);

		if (element instanceof AndroidElement) {
			element.click();
		} else {
			highLight(driver, element);
			element.click();
		}

		verificaSleep(segundos);

		return element;
	}

	public static <T extends WebElement> T comboBox(WebDriver driver, Enumeradores.locators locator,
			String valorLocator, String valor) {
		return comboBox(driver, locator, valorLocator, valor, 0);
	}

	public static <T extends WebElement> T comboBox(WebDriver driver, Enumeradores.locators locator,
			String valorLocator, String valor, int segundos) {

		T element;

		element = elemento(driver, locator, valorLocator);

		if (element instanceof AndroidElement) {
			new Select(element).selectByVisibleText(valor);
		} else {
			highLight(driver, element);
			new Select(element).selectByVisibleText(valor);
		}

		verificaSleep(segundos);

		return element;
	}

	public static void navegar(WebDriver driver, String url) {
		navegar(driver, url, 0);
	}

	public static void navegar(WebDriver driver, String url, int segundos) {
		driver.get(url);

		verificaSleep(segundos);
	}

	public static void sair(WebDriver driver) {
		driver.quit();
	}

	// Login para apps (sem URL) e não possui espera
	public static void login(WebDriver driver, Enumeradores.locators locator, String valorLocator_1,
			String valorLocator_2, String valorLocator_3, String usuario, String senha) {
		login(driver, null, locator, valorLocator_1, valorLocator_2, valorLocator_3, usuario, senha, 0);
	}

	// Login para apps (sem URL) e possui espera
	public static void login(WebDriver driver, Enumeradores.locators locator, String valorLocator_1,
			String valorLocator_2, String valorLocator_3, String usuario, String senha, int segundos) {
		login(driver, null, locator, valorLocator_1, valorLocator_2, valorLocator_3, usuario, senha, segundos);
	}

	// Login para WebApps (com URL) e não possui espera
	public static void login(WebDriver driver, String url, Enumeradores.locators locator, String valorLocator_1,
			String valorLocator_2, String valorLocator_3, String usuario, String senha) {
		login(driver, url, locator, valorLocator_1, valorLocator_2, valorLocator_3, usuario, senha, 0);
	}

	// Login para WebApps (com URL) e possui espera
	public static void login(WebDriver driver, String url, Enumeradores.locators locator, String valorLocator_1,
			String valorLocator_2, String valorLocator_3, String usuario, String senha, int segundos) {

		if (url != null && !"".equals(url)) {
			navegar(driver, url);
			digitar(driver, locator, valorLocator_1, usuario, 1);
			digitar(driver, locator, valorLocator_2, senha, 1);
			clicar(driver, locator, valorLocator_3, 1);
		} else {
			digitar(driver, locator, valorLocator_1, usuario, 1);
			digitar(driver, locator, valorLocator_2, senha, 1);
			clicar(driver, locator, valorLocator_3, 1);
		}

		verificaSleep(segundos);
	}

	public static void print(WebDriver driver, String path) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path));
	}

}
