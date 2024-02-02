package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN

    // END
}
