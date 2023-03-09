package in.eric.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//API is an Application programming interface which allows two or more services to communicate among each other
//It is a collection of URL and each URL contains an end point

//architecture of the Rest API
//controller <--> service <--> repository(database layer


@SpringBootApplication
@EnableMongoRepositories("in.eric.springbootmongodb")
public class SpringbootmongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongodbApplication.class, args);
	}

}
