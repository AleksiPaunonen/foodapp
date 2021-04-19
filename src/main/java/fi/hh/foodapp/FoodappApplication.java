package fi.hh.foodapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fi.hh.foodapp.domain.Food;
import fi.hh.foodapp.domain.FoodRepository;
import fi.hh.foodapp.domain.Category;
import fi.hh.foodapp.domain.CategoryRepository;


@SpringBootApplication
public class FoodappApplication {
	
	@Configuration
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers("/h2-console/**").permitAll();

	        http.csrf().disable();
	        http.headers().frameOptions().disable();
	    }
	}
	
	private static final Logger log = LoggerFactory.getLogger(FoodappApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FoodappApplication.class, args);
	}

	@Bean
	public CommandLineRunner foodDemo(FoodRepository frepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("saving couple of foods and categories");
			
			Category category1 = new Category("Liharuoka");
			crepository.save(category1);
			
			Category category2 = new Category("Keittoruoka");
			crepository.save(category2);
			
			Category category3 = new Category("Kasvisruoka");
			crepository.save(category3);
			
			frepository.save(new Food("Lihapullat ja muusi", "Naudan jauhelihaa ja perunamuusia", "45min", "Tulossa", category1));
			frepository.save(new Food("Itämainen kanakeitto", "Kanaa, bataattia, paprikaa ja porkkanaa", "40min", "Tulossa", category2));
			frepository.save(new Food("Sienirisotto", "Satokauden sieniä", "35min", "Tulossa", category3));
			
			
			log.info("fetch all foods");
			for (Food food : frepository.findAll()) {
				log.info(food.toString());
			}
		};
	}
}
