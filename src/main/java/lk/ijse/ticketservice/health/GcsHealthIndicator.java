package lk.ijse.ticketservice.health;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("gcp")
@Component("firestoreHealth")
public class GcsHealthIndicator implements HealthIndicator {

    private final Firestore firestore;

    @Value("${gcp.firestore.collection:order-audit-logs}")
    private String collection;

    public GcsHealthIndicator(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Health health() {
        try {
            firestore.collection(collection).limit(1).get().get();
            return Health.up()
                    .withDetail("collection", collection)
                    .withDetail("service", "Firestore")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("collection", collection)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
