package ca.ivar.MyListOfStuffToDo;

import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

//@EnableJpaRepositories("ca.ivar.MyListOfStuffToDo.*")
//@ComponentScan(basePackages = { "ca.ivar.MyListOfStuffToDo.*" })
//@EntityScan(basePackages = {"ca.ivar.MyListOfStuffToDo.model"})
public class MyListOfStuffToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyListOfStuffToDoApplication.class, args);
	}

}
