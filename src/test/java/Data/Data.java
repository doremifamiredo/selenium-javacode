package Data;

import lombok.AllArgsConstructor;
import net.datafaker.Faker;

import java.util.Locale;

public class Data {

    public static final Faker faker = new Faker(new Locale("ru"));

    @AllArgsConstructor
    public static class User {
        public String login;
        public String password;
        public String name;
    }

    public static User getValidUser() {
        return new User("somov_oleg", "DY;nwmkgzpnNx9n", "Олег С");
    }


}
