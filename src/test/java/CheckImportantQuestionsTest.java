import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.ScooterHomePage;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class CheckImportantQuestionsTest {

    private WebDriver driver;

    @Before
    public void startUp() {
//      Для запуска тестов на Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
//      Для запуска тестов на Firefox
//      WebDriverManager.firefoxdriver();
//      driver = new FirefoxDriver();
    }

    private final String importantQuestionsHeader;
    private final String expectedTextHeader;
    private final String importantQuestionsPanel;
    private final String expectedTextPanel;

    public CheckImportantQuestionsTest(String importantQuestionsHeader, String expectedTextHeader, String importantQuestionsPanel, String expectedTextPanel) {
        this.importantQuestionsHeader = importantQuestionsHeader;
        this.expectedTextHeader = expectedTextHeader;
        this.importantQuestionsPanel = importantQuestionsPanel;
        this.expectedTextPanel = expectedTextPanel;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { "accordion__heading-0", "Сколько это стоит? И как оплатить?", "accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "accordion__heading-1", "Хочу сразу несколько самокатов! Так можно?", "accordion__panel-1",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { "accordion__heading-2", "Как рассчитывается время аренды?","accordion__panel-2",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { "accordion__heading-3", "Можно ли заказать самокат прямо на сегодня?", "accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { "accordion__heading-4", "Можно ли продлить заказ или вернуть самокат раньше?", "accordion__panel-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { "accordion__heading-5", "Вы привозите зарядку вместе с самокатом?", "accordion__panel-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { "accordion__heading-6", "Можно ли отменить заказ?", "accordion__panel-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { "accordion__heading-7", "Я жизу за МКАДом, привезёте?", "accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkBlockWithImportantQuestions() {

        // Создаем объект главной страницы
        ScooterHomePage homePage = new ScooterHomePage(driver);

        // Переходим на главную страницу
        homePage.openScooterHomePage();

        // Принимаем куки
        homePage.acceptCookie();

        // Клик по строке из блока "Вопросы о важном"
        homePage.clickImportantQuestionsHeader(importantQuestionsHeader);

        // Проверяем текст в строке "Вопросы о важном"
        assertEquals(expectedTextHeader, homePage.getTextHeader(importantQuestionsHeader));

        // Проверка текста в "Вопросы о важном" в выпадающем меню
        assertEquals(expectedTextPanel, homePage.getTextPanel(importantQuestionsPanel));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
