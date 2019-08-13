package ru.asavt.spring.earn.learn.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.asavt.spring.earn.learn.model.User;
import ru.asavt.spring.earn.learn.repository.UserDetailsRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        return map -> {
            String id = (String) map.get("sub");
            User user = userDetailsRepo.findById(id).orElseGet(() -> {
                User user1 = new User();
                user1.setId(id);
                user1.setEmail((String) map.get("email"));
                user1.setGender((String) map.get("gender"));
                user1.setLocale((String) map.get("locale"));
                user1.setName((String) map.get("name"));
                user1.setUserpic((String) map.get("picture"));
                return user1;
            });
            user.setLastVisit(LocalDateTime.now());


            return userDetailsRepo.save(user);
        };
    }
}
