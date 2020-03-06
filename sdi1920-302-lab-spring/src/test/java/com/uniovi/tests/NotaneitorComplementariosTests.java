package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\kendo\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	} // Antes de la primera prueba

	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Registro de usuarios con datos válidos.
	@Test
	public void PR01() {
		PO_PrivateView.login(driver, "99999988F", "123456");
		List<WebElement> elementos = PO_HomeView.checkElement(driver, "free", "//li[contains(@id,'professor-menu')]/a");
		elementos.get(0).click();
		elementos = PO_HomeView.checkElement(driver, "free", "//a[contains(@href, '/professor/add')]");
		elementos.get(0).click();

		PO_PrivateView.fillFormAddUser(driver, "11111111Q", "Pablo", "Cañal Suárez", "Cat 1");
		PO_View.checkElement(driver, "text", "11111111Q");
	}

	// Registro de usuarios con datos no válidos.
	@Test
	public void PR02() {
		PO_PrivateView.login(driver, "99999988F", "123456");
		List<WebElement> elementos = PO_HomeView.checkElement(driver, "free", "//li[contains(@id,'professor-menu')]/a");
		elementos.get(0).click();
		elementos = PO_HomeView.checkElement(driver, "free", "//a[contains(@href, '/professor/add')]");
		elementos.get(0).click();

		PO_PrivateView.fillFormAddUser(driver, "1", "P", "C", "C");
		PO_View.checkElement(driver, "text", "El nombre debe tener entre 5 y 24 caracteres.");
		PO_View.checkElement(driver, "text", "El apellido debe tener entre 5 y 24 caracteres.");
		PO_View.checkElement(driver, "text", "El DNI debe tener entre 5 y 24 caracteres.");
		PO_View.checkElement(driver, "text", "La categoria no es válida");
	}
	
	@Test
	public void PR03() {
		PO_PrivateView.login(driver, "99999990A", "123456");
		List<WebElement> elementos = PO_HomeView.checkElement(driver, "free", "//li[contains(@id,'professor-menu')]/a");
		elementos.get(0).click();
		
		SeleniumUtils.textoNoPresentePagina(driver, "Agregar Profesor");
	}

}