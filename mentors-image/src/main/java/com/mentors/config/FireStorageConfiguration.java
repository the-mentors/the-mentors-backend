package com.mentors.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FireStorageConfiguration {
    private final FireStorageProperties properties;

    @Bean
    public void initialize() {
        try {
            ClassPathResource serviceAccount = new ClassPathResource(properties.getFirebaseStorage());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(properties.getBucket())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
