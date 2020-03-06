package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void login(WebDriver driver, String dniF, String passwordF) {
		clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, dniF, passwordF);
		checkElement(driver, "text", "Notas del usuario");
		SeleniumUtils.esperarSegundos(driver, 1);
	}

	public static void addMark(WebDriver driver, int i, String nota, String score) {
		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = checkElement(driver, "free", "//li[contains(@id,'marks-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
		// Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
		fillFormAddMark(driver, 3, nota, score);

		// Esperamos a que se muestren los enlaces de paginación la lista de notas
		elementos = checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
		// Comprobamos que aparece la nota en la pagina
		elementos = checkElement(driver, "text", nota);
	}

	public static void endTest(WebDriver driver) {
		clickOption(driver, "logout", "text", "Identifícate");
	}

	public static void listNotasAndDeleteLast(WebDriver driver, String string) {
		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de notas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'mark/list')]");
		elementos.get(0).click();
		// Esperamos a que se muestren los enlaces de paginacion la lista de notas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
		// Esperamos a que aparezca la Nueva nota en la ultima pagina
		// Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
		// td[contains(text(), 'Nota Nueva
		// 1')]/following-sibling::*/a[contains(text(),'mark/delete')]"
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();
		// Volvemos a la última pagina
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(3).click();
		// Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1", PO_View.getTimeout());
	}

	public static void fillFormAddUser(WebDriver driver, String dniF, String nameF, String surnameF, String catF) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);		
		WebElement dni = driver.findElement(By.name("dni"));
		dni.clear();
		dni.sendKeys(dniF);
		
		WebElement name = driver.findElement(By.name("nombre"));
		name.clear();
		name.sendKeys(nameF);
		
		WebElement surname = driver.findElement(By.name("apellido"));
		surname.clear();
		surname.sendKeys(surnameF);
		
		WebElement cat = driver.findElement(By.name("categoria"));
		cat.clear();
		cat.sendKeys(catF);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}