package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterHomePage {

    private final WebDriver driver;
    // Кнопка принять куки "да все привыкли'
    private final By cookieButton = By.id("rcc-confirm-button");
    // Кнопка "Заказать" вверху страницы
    private final String orderUpButton = ".//button[@class='Button_Button__ra12g']";
    // Кнопка "Заказать" внизу страницы
    private final String orderDownButton = ".//div[@class='Home_FinishButton__1_cWm']/button";

    public ScooterHomePage(WebDriver driver) {this.driver = driver;}

    // Открывает главную страницу
    public void openScooterHomePage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Принять куки
    public void acceptCookie() {
        driver.findElement(cookieButton).isDisplayed();
        driver.findElement(cookieButton).click();
    }

    // Клик по строке "Вопросы о важном"
    public void clickImportantQuestionsHeader(String importantQuestionsHeader) {
        WebElement element = driver.findElement(By.id(importantQuestionsHeader));
        // Скролл до нужного элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.id(importantQuestionsHeader)));
        // Кликаем по строке
        element.click();
    }

    // Получение текста из строки "Вопросы о важном"
    public String getTextHeader(String importantQuestionsHeader) {
        WebElement element = driver.findElement(By.id(String.valueOf(importantQuestionsHeader)));
        String actualTextHeader = element.getText();
        return actualTextHeader;
    }

    // Получение текста из выпавшей панели
    public String getTextPanel (String importantQuestionsPanel) {
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(importantQuestionsPanel)));
        String actualTextPanel = driver.findElement(By.id(importantQuestionsPanel)).getText();
        return actualTextPanel;
    }

    // Клик по кнопке "Заказать"
    public void clickOrderButton(int n) {

        if (n==1) {
            driver.findElement(By.xpath(orderUpButton)).click();
        } else {
            // Скролл до элемента
            WebElement element = driver.findElement(By.xpath(orderDownButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(orderDownButton)).click();
        }
    }
}
