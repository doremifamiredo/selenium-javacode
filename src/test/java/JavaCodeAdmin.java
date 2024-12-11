import Pages.BaseSeleniumTest;
import Pages.Dashboard;
import Pages.LoginPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class JavaCodeAdmin extends BaseSeleniumTest {

    private static final Faker faker = new Faker(new Locale("ru"));
    private String expected;

    @BeforeEach
    void login() {
        LoginPage loginPage = new LoginPage();
        Dashboard dashboard = loginPage.authorizationAdmin();
        assertAll(() -> assertEquals("Олег С", dashboard.getNameUserProfile()),
                () -> assertEquals("Панель администратора", driver.getTitle()));
        expected = faker.word().noun();
    }

    @Test
    @DisplayName("2. Добавление нового интервью")
    void addingInterview() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Интервью").addInterview(expected);
        assertEquals(expected, dashboard.firstBox.getText());
    }

    @Test
    @DisplayName("3. добавление нового вопроса")
    void newQuestion() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Вопросы").addNote(expected);
        assertEquals(expected, dashboard.firstDiv.getText());
    }

    @Test
    @DisplayName("4. создание квиза")
    void newQuiz() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Квизы").addNote(expected);
        assertEquals(expected, dashboard.firstBox.getText());
    }

    @Test
    @DisplayName("5. создание нового модуля")
    void newModule() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Модули").addModule(expected);
        assertEquals(expected, dashboard.firstBox.getText());
    }

    @Test
    @DisplayName("6. создание нового курса")
    void newCourse() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Курсы").addCourse(expected);
        assertEquals(expected, dashboard.firstBox.getText());
    }

    @ParameterizedTest
    @MethodSource("Pages.Generator#userInfo")
    @DisplayName("7. создание нового пользователя")
    void newUser(HashMap<String, String> user) throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Пользователи").addUser(user);
        assertAll(() -> assertEquals(user.get("Логин"), dashboard.login.getText(), "Логины не совпадают"),
                () -> assertEquals(user.get("Имя"), dashboard.name.getText(), "Имя не совпадает"),
                () -> assertEquals(user.get("Фамилия"), dashboard.lastName.getText(), "Фамилия не совпадает"));
    }

    @ParameterizedTest
    @MethodSource("Pages.Generator#interviewInfo")
    @DisplayName("9. редактирование интервью")
    void editingInterview(HashMap<String, String> interview) throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        String actual = dashboard.menu("Интервью").editingInterview(interview);
        assertTrue(interview.containsValue(actual));
    }

    @Test
    @DisplayName("10. создание нового экзамена")
    void newExam() throws InterruptedException {
        Dashboard dashboard = new Dashboard();
        dashboard.menu("Экзамены").addModule(expected);
        assertEquals(expected, dashboard.firstBox.getText());
    }
}
