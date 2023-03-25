package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ScooterOrderPage {
    private final WebDriver driver;
    public ScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввод имени в поле "Имя"
    public ScooterOrderPage inputName(String name) {
        driver.findElement(By.xpath("//input[@placeholder= '* Имя']")).sendKeys(name);
        return this;
    }

    // Ввод фамилии в поле "Фамилия"
    public ScooterOrderPage inputSurname(String surname) {
        driver.findElement(By.xpath("//input[@placeholder= '* Фамилия']")).sendKeys(surname);
        return this;
    }

    // Ввод адреса в поле "Адрес: куда привезти заказ"
    public ScooterOrderPage inputAddress(String address) {
        driver.findElement(By.xpath("//input[@placeholder= '* Адрес: куда привезти заказ']")).sendKeys(address);
        return this;
    }

    // Клик по полю "Станция метро"
    public void clickMetroStation() {
        driver.findElement(By.className("select-search")).click();
    }

    // Ввод станции метро и выбор из списка
    public void chooseMetroStation(String metroStation) {
        driver.findElement(By.className("select-search__input")).sendKeys(metroStation);
        driver.findElement(By.className("select-search__select")).click();
    }

    // Ввод телефона в поле "Телефон: на него позвонит курьер"
    public ScooterOrderPage inputPhone(String phone) {
        driver.findElement(By.xpath("//input[@placeholder= '* Телефон: на него позвонит курьер']")).sendKeys(phone);
        return this;
    }

    // Клик по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Переход на следующий экран "Про аренду"
    // Кликнуть по полю "Когда привезти самокат" , поставить нужную дату и нажать Enter
    public ScooterOrderPage inputDate(String date) {
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(date);
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(Keys.ENTER);
        return this;
    }

    // Клик по полю "Срок аренды" и выбор срока
    public void clickRentPeriod() {
        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.className("Dropdown-option")).click();
    }

    // Клик по полю "Цвет самоката" и выбор цвета
    public void clickScooterColor(String scooterColor) {
        driver.findElement(By.id(scooterColor)).click();
    }

    // Клик по полю "Комментарий для курьера" и ввод комментария
    public ScooterOrderPage inputCourierComment(String courierComment) {
        driver.findElement(By.xpath("//input[@placeholder= 'Комментарий для курьера']")).sendKeys(courierComment);
        return this;
    }

    // Клик по кнопке "Заказать"
    public void clickOrderButton() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Клик по кнопке "Да"
    public void clickYesButton() {
        driver.findElement(By.xpath("//button[text()='Да']")).click();
    }

    // Получаем текст о подтверждении заказа
    public String getCompletedOrderText(){
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }

}