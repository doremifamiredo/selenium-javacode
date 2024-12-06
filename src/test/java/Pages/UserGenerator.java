package Pages;

import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UserGenerator {
    @FindBy(xpath = "//input[@class]/preceding-sibling::*[contains(text(), 'Имя')]")
    WebElement name;

    private static final String[] SALE_STATUS = new String[]{"active_search", "on_project", "pause_search", ""};
    private static final String[] ROLES = new String[]{"admin", "user", ""};
    private static final String SPECIAL_CHARTERS = "!@#$%^&*_+=;";
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static Faker faker = new Faker();
    public static Faker fakerRU = new Faker(new Locale("ru"));

    public static Stream<HashMap<String, String>> dataFakerStream() {
        return Stream.generate(new Supplier<HashMap<String, String>>() {
            @Override
            public HashMap<String, String> get() {
                HashMap<String, String> user = new HashMap<>();
                user.put("Имя", getName());
                user.put("Фамилия", getLastname());
                user.put("Эл. почта", getEmail());
                user.put("Логин", getUsername());
                user.put("Пароль", getPassword());
                user.put("Роли", ROLES[faker.random().nextInt(ROLES.length)]);
                return user;
            }
        }).limit(10);
    }

    private static String getName() {
        return switch (faker.random().nextInt(5)) {
            case 1 -> faker.name().firstName();
            case 2 -> fakerRU.name().firstName();
            case 3 ->
                    faker.name().lastName() + SPECIAL_CHARTERS.charAt(faker.random().nextInt(SPECIAL_CHARTERS.length()));
            default -> "";
        };
    }

    private static String getLastname() {
        return switch (faker.random().nextInt(5)) {
            case 1 -> faker.name().lastName();
            case 2 -> fakerRU.name().lastName();
            case 3 ->
                    faker.name().lastName() + SPECIAL_CHARTERS.charAt(faker.random().nextInt(SPECIAL_CHARTERS.length()));
            default -> "";
        };
    }

    private static String getEmail() {
        return switch (faker.random().nextInt(5)) {
            case 1 -> faker.internet().emailAddress();
            case 2 -> faker.internet().username();
            case 3 -> fakerRU.internet().username();
            default -> "";
        };
    }

    private static String getUsername() {
        return switch (faker.random().nextInt(5)) {
            case 1 -> faker.internet().username();
            case 2 -> String.join("", SPECIAL_CHARTERS);
            case 3 -> fakerRU.internet().username();
            default -> "";
        };
    }

    private static String getPassword() {
        return switch (faker.random().nextInt(6)) {
            case 1 -> faker.internet().username() + faker.random().nextInt();
            case 2 -> String.join("", SPECIAL_CHARTERS);
            case 3 -> IntStream.range(0, 10)
                    .map(i -> faker.random().nextInt(RUSSIAN_ALPHABET.length()))
                    .mapToObj(RUSSIAN_ALPHABET::charAt)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            case 4 -> faker.internet().password();
            default -> "";
        };
    }
}
