package hexlet.code.component;
import java.util.stream.IntStream;

import hexlet.code.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
//import hexlet.code.service.CustomUserDetailsService;
import net.datafaker.Faker;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    public DataInitializer(UserRepository userRepository, CustomUserDetailsService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    private final UserRepository userRepository;
    private final CustomUserDetailsService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        User userData = new User();
        userData.setEmail(email);
        userData.setPasswordDigest("qwerty");
        userService.createUser(userData);

        var user = userRepository.findByEmail(email).get();

    }
}
