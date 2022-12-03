package org.spring.course;

import org.spring.course.domain.entity.Customer;
import org.spring.course.domain.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SalesApplication {
    @Bean
    public CommandLineRunner init(@Autowired CustomersRepository customersRepository){
        return args -> {
            customersRepository.save(new Customer("Teste-Fernando"));
            customersRepository.save(new Customer("Edson-Teste-Edson"));
            customersRepository.save(new Customer("123Testeson"));

            System.out.println("----------GET ALL------------");
            List<Customer> customers = customersRepository.getAll();
            customers.forEach(System.out::println);

            System.out.println("----------UPDATING------------");

            Customer testeUpdate = customersRepository.update(new Customer(1, "Edson Fernando"));
            System.out.println(testeUpdate);

            System.out.println("----------SEARCHING BY NAME-------------");


            customers = customersRepository.searchByName("Teste");
            customers.forEach(System.out::println);

            System.out.println("----------DELETING-------------");

            customersRepository.delete(1);
            customers = customersRepository.getAll();
            customers.forEach(System.out::println);

            System.out.println("----------OK-------------");


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class);
    }
}
