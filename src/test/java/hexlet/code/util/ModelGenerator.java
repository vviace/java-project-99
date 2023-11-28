package hexlet.code.util;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hexlet.code.model.User;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;


@Component
public class ModelGenerator {
    private Model<User> userModel;
    @Autowired
    private Faker faker;

    @PostConstruct
    private void init() {
        userModel = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .toModel();
    }

    public Model<User> getUserModel() {
        return userModel;
    }

    public void setUserModel(Model<User> userModel) {
        this.userModel = userModel;
    }


    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }
}
