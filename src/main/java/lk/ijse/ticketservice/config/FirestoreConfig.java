package lk.ijse.ticketservice.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("gcp")
@Configuration
public class FirestoreConfig {

    @Value("${gcp.project-id:eca-attachment-service}")
    private String projectId;

    @Bean
    public Firestore firestore() {
        return FirestoreOptions.newBuilder()
                .setProjectId(projectId)
                .build()
                .getService();
    }
}
