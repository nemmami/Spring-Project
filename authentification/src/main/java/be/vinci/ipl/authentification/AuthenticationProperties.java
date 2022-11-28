package be.vinci.ipl.authentification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "be.vinci.ipl.authentication")
@Getter
@Setter
public class AuthenticationProperties {

    private String secret;

}
