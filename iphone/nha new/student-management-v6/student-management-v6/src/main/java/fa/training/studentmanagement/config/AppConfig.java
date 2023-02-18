package fa.training.studentmanagement.config;

import fa.training.studentmanagement.component.PasswordEncrypt;
import fa.training.studentmanagement.component.impl.PasswordEncryptSHA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncrypt passwordEncrypt() {
        return new PasswordEncryptSHA();
    }
}
