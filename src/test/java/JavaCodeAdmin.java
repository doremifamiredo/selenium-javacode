import Data.Data;
import Pages.Dashboard;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Data.Data.faker;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class JavaCodeAdmin {
    private final String adminLogin = "somov_oleg";
    private final String adminPassword = "DY;nwmkgzpnNx9n";
    private String idQuestion;
    private String idModule;

    @org.junit.jupiter.api.Test
    @DisplayName("1. Авторизация в портале")
    void authorizationOnThePortal() {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        assertAll(() -> assertEquals("Олег С", dashboard.getNameUserProfile()),
                () -> assertEquals("Панель администратора", driver.getTitle()));
    }

    @Test
    @DisplayName("2. Добавление нового интервью")
    void addingInterview() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Интервью")));
        link.click();
    }

    @Test
    void newQuestion() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Вопросы')]")).click();
    }

    @Test
    void newQuiz() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Квизы')]")).click();
    }

    @Test
    void newModule() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Модули')]")).click();
    }

    @Test
    void newCourse() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Курсы')]")).click();
    }

    @Test
    void newUser() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Пользователи')]")).click();
    }

    @Test
    void newExam() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = loginPage.authorization(adminLogin, adminPassword);
        driver.findElement(By.xpath("//a[contains(text(), 'Экзамены')]")).click();
    }

    public static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void openSite() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //   options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://aqa-admin.javacode.ru/login");
        driver.manage().window().maximize();
    }

    @AfterEach
    void closing() {
        driver.close();
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }
}
