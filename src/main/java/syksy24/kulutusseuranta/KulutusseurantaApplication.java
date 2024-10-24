package syksy24.kulutusseuranta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import syksy24.kulutusseuranta.domain.Category;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.ExpenseRepository;
import syksy24.kulutusseuranta.domain.AppUser;
import syksy24.kulutusseuranta.domain.AppUserRepository;

@SpringBootApplication
public class KulutusseurantaApplication {
    private static final Logger log = LoggerFactory.getLogger(KulutusseurantaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KulutusseurantaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(
            @Autowired CategoryRepository categoryRepository,
            @Autowired ExpenseRepository expenseRepository,
            @Autowired AppUserRepository userRepository) {
        return (args) -> {
            log.info("Creating sample categories");

            // Luodaan esimerkkikategorioita
            Category food = new Category("Ruoka");
            Category rent = new Category("Vuokra");
            Category hobbies = new Category("Harrastukset");

            categoryRepository.save(food);
            categoryRepository.save(rent);
            categoryRepository.save(hobbies);

            log.info("Saving sample expenses");
            // Luodaan esimerkkikuluja
            expenseRepository.save(new Expense("Ruoka", 50.0, LocalDate.now(), "Supermarket", food));
            expenseRepository.save(new Expense("Vuokra", 800.0, LocalDate.now(), "Apartment", rent));
            expenseRepository.save(new Expense("Harrastukset", 30.0, LocalDate.now(), "Cinema", hobbies));

            // Luodaan esimerkkikäyttäjiä
            AppUser user1 = new AppUser("user", "$2a$10$GCNSYY8Rs9zV0YL0lainN.XBKOlq8/nB4Sf/voyCWKPzgSVupKIiW", "appuser@appuser.com", "ROLE_USER");
            AppUser user2 = new AppUser("admin", "$2a$10$Oj9Tv75nMXoHQzwxTo1Tseuxf0jwEVkykBFQ6BfI6Ny75GAj0sOjq", "admin@admin.com", "ROLE_ADMIN");

            userRepository.save(user1);
            userRepository.save(user2);

            log.info("Listing categories and expenses");
            for (Category category : categoryRepository.findAll()) {
                log.info(category.toString());
            }

            for (Expense expense : expenseRepository.findAll()) {
                log.info(expense.toString());
            }
        };
    }
}