package in.eric.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("in.eric.springbootmongodb")
public class SpringbootmongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongodbApplication.class, args);
	}

}
