package org.ibs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TestTwo extends BaseTest {
    @Tag("ST_2")
    @ParameterizedTest
    @MethodSource("testDataTwo")
    void testAddingExoticProduct(String nameProduct, String type, String ruType, String numberString){

// Раскрываем окно браузера на весь экран
        driver.manage().window().maximize();

// Открываем начальную страницу
        driver.get("http://localhost:8080/food");

// Проверяем, что открылась базовая страница
        WebElement productListHeader = driver.findElement(By.xpath("//h5[text()='Список товаров']"));
        Assertions.assertEquals("Список товаров", productListHeader.getText());

// Нажимаем кнопку добавить
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Добавить']"));
        addButton.click();

// Проверяем, что отобразилось окно открытия товара
        WebElement addProduct = driver.findElement(By.xpath("//h5[text()='Добавление товара']"));
        Assertions.assertEquals("Добавление товара",
                wait.until(ExpectedConditions.visibilityOf(addProduct)).getText());

// Вводим наименование
        WebElement fieldName = driver.findElement(By.id("name"));
        fieldName.sendKeys(nameProduct);
        Assertions.assertEquals(nameProduct, fieldName.getAttribute("value"));

// Делаем выбор в дроп-меню
// Проверяем, что зафиксировался выбор в дроп-меню
        Select typeSelectionField = new Select(driver.findElement(By.id("type")));
        typeSelectionField.selectByValue(type);
        Assertions.assertEquals(type, driver.findElement(By.id("type")).getAttribute("value"),
                "Выбран овощ");

// Нажимаем на чек-бокс и проверяем, что он выбран
        WebElement exoticCheckBox = driver.findElement(By.id("exotic"));
        exoticCheckBox.click();
        Assertions.assertTrue(exoticCheckBox.isSelected(), "Чек-бокс не выбран");

// Нажимаем кнопку сохранить
        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

// Проверяем, что появилась новая строка с созданным фруктом
        Assertions.assertEquals(numberString,
                driver.findElement(By.xpath("//tr[" + numberString + "]/th")).getText());

// Проверяем, что появилось название созданного фрукта
        Assertions.assertEquals(nameProduct,
                driver.findElement(By.xpath("//tr[" + numberString + "]/td[1]")).getText());

// Проверяем, Что появился тип созданного фрукта
        Assertions.assertEquals(ruType,
                driver.findElement(By.xpath("//tr[" + numberString + "]/td[2]")).getText());

// Проверяем, что отображается экзотичность созданного фрукта
        Assertions.assertEquals("true",
                driver.findElement(By.xpath("//tr[" + numberString + "]/td[3]")).getText());
    }
}
