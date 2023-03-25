import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.ScooterHomePage;
import pageobjects.ScooterOrderPage;

import static org.hamcrest.CoreMatchers.containsString;



//Параметризированный тест
@RunWith(Parameterized.class)
public class CheckOrderScooterTest {

    private WebDriver driver;

    @Before
    public void startUp() {

// 		Для запуска тестов на Firefox
//      WebDriverManager.firefoxdriver().clearDriverCache().setup();
//      driver = new FirefoxDriver();
// 		Для запуска тестов на Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
    }


    private final int n;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String scooterColor;
    private final String courierComment;


    public CheckOrderScooterTest(int n, String name, String surname, String address, String metroStation, String phone, String date, String scooterColor, String courierComment) {
        this.n = n;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.scooterColor = scooterColor;
        this.courierComment = courierComment;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {1, "Антон", "Павлов", "ул. Никольская, д.12", "Лубянка", "+71111099333", "20.05.2023", "grey", "Это тестовый комментарий"},
                {2, "Павел", "Антонов", "ул. Гончарная, д.34", "Таганская", "+71111099332", "22.05.2023", "black", "Это тестовый комментарий"},
        };
    }

    @Test
    public void orderScooterTest() {
        // Создаем объект главной страницы заказа
        ScooterHomePage homePage = new ScooterHomePage(driver);
        // Переход на главную страницу приложения
        homePage.openScooterHomePage();
        // Принимаем куки
        homePage.acceptCookie();
        // Клик по кнопке "Заказать"
        homePage.clickOrderButton(n);
        // Создаем объект со страницей заказа самоката
        ScooterOrderPage scooterOrderPage = new ScooterOrderPage(driver);
        // Ввод имени в поле "Имя"
        scooterOrderPage.inputName(name);
        // Ввод фамилии в поле "Фамилия"
        scooterOrderPage.inputSurname(surname);
        // Ввод адреса в поле "Адрес: куда привезти заказ"
        scooterOrderPage.inputAddress(address);
        // Клик по полю "Станция метро"
        scooterOrderPage.clickMetroStation();
        // Ввод станции метро и выбор из списка
        scooterOrderPage.chooseMetroStation(metroStation);
        // Ввод телефона в поле "Телефон: на него позвонит курьер"
        scooterOrderPage.inputPhone(phone);
        // Клик по кнопке "Далее"
        scooterOrderPage.clickNextButton();
        // Перешли на следующий экран "Про аренду"
        // Кликнуть по полю "Когда привезти самокат", поставить нужную дату и нажать Enter
        scooterOrderPage.inputDate(date);
        // Клик по полю "Срок аренды" и выбор срока
        scooterOrderPage.clickRentPeriod();
        // Клик по полю "Цвет самоката" и выбор цвета
        scooterOrderPage.clickScooterColor(scooterColor);
        // Ввод комментария в Поле "Комментарий для курьера"
        scooterOrderPage.inputCourierComment(courierComment);
        // Клик по кнопке "Заказать"
        scooterOrderPage.clickOrderButton();
        // Клик по кнопке "Да"
        scooterOrderPage.clickYesButton();
        // Получаем текст о подтверждении заказа
        MatcherAssert.assertThat("Не появилось окно - Заказ оформлен",
                scooterOrderPage.getCompletedOrderText(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
