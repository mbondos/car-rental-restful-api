package tk.mbondos;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tk.mbondos.Entities.Car;
import tk.mbondos.Entities.Customer;
import tk.mbondos.Entities.Embeddable.Address;
import tk.mbondos.Entities.Rental;
import tk.mbondos.Repositories.CarRepository;
import tk.mbondos.Repositories.CustomerRepository;
import tk.mbondos.Repositories.RentalRepository;

import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication
@EnableAutoConfiguration

@ComponentScan(basePackages = "tk.mbondos.Controllers")
@EntityScan("tk.mbondos.Entities")
@EnableJpaRepositories("tk.mbondos.Repositories")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(CarRepository carRepository,
								  RentalRepository rentalRepository,
								  CustomerRepository customerRepository) {
		return (evt) -> Arrays.asList(
				"Jones,Smith,Williams".split(","))
				.forEach(
						a -> {
							Car car = carRepository.save(new Car(
									"Ferrari",
									"LaFerrari",
									19.90,
									190000,
									2013));

							carRepository.save(new Car(
									"Ferrari",
									"F40",
									199.90,
									40000,
									1990));

							Customer customer = customerRepository.save(new Customer(
									"John",
									a,
									new Address(
											"6565 Long Street",
											"Fargo",
											"243234")));

							rentalRepository.save(new Rental(
									LocalDate.of(2017,5,23),
									LocalDate.of(2017,5,24),
									car,
									customer));

						});

	}



}
