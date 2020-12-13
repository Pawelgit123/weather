package com.sda.weather;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import com.sda.weather.security.User;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
@EnableSwagger2
@EnableJpaAuditing
@EnableScheduling
@Slf4j
public class WeatherApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Scheduled(cron = "1 16 * * 1 *")
    public void generateRaport(){
        log.info("At this point, we have "+ userRepository.count() +" users");
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User michal = new User();
        michal.setUsername("michal");
        michal.setPassword(passwordEncoder.encode("michal1"));
        michal.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
        userRepository.save(michal);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin1"));
        admin.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
        userRepository.save(admin);
    }
}
