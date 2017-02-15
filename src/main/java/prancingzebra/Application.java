package prancingzebra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	@Profile("test")
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		return flyway -> {
			flyway.clean();
			flyway.migrate();
		};
	}

}
