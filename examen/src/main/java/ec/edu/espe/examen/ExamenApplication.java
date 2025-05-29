package ec.edu.espe.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ec.edu.espe.examen")
@EntityScan("ec.edu.espe.examen.modelo")
@EnableJpaRepositories("ec.edu.espe.examen.repositorio")
public class ExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}

}
