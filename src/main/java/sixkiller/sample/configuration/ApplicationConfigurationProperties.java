package sixkiller.sample.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sixkiller.sample.domain.User;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@ConfigurationProperties(prefix="appconfig")
public class ApplicationConfigurationProperties {

    @Autowired
    private MongoTemplate mongoTemplate;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() throws IOException {
        User admin = new User();
        String[] roles = {"ADMIN", "USER"};
        admin.setUserName("nmy3");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(roles);
        mongoTemplate.save(admin);
    }

    private String[] defaultUserRoles;

    private String clientId;

    private String clientSecret;

    private String[] onPopStateUrls;

    public String[] getDefaultUserRoles() {
        return defaultUserRoles;
    }

    public void setDefaultUserRoles(String[] defaultUserRoles) {
        this.defaultUserRoles = defaultUserRoles;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String[] getOnPopStateUrls() {
        return onPopStateUrls;
    }

    public void setOnPopStateUrls(String[] onPopStateUrls) {
        this.onPopStateUrls = onPopStateUrls;
    }
}
