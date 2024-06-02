package ec.edu.uce.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "ec.edu.uce.persistence.state")
@EnableJpaRepositories(basePackages = "ec.edu.uce.persistence.interfaces")
public class PersistenceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersistenceApplication.class, args);
	}
}
