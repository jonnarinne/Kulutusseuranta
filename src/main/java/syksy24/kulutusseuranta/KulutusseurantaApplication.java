package syksy24.kulutusseuranta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import syksy24.kulutusseuranta.domain.Category;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.ExpenseRepository;

@SpringBootApplication
public class KulutusseurantaApplication {
    private static final Logger log = LoggerFactory.getLogger(KulutusseurantaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KulutusseurantaApplication.class, args);
    }

    /* 
    @Bean
    public CommandLineRunner demoData(
            @Autowired CategoryRepository categoryRepository,
            @Autowired ExpenseRepository expenseRepository,
            @Autowired AppUserRepository userRepository) {
        return (args) -> {
            log.info("Creating sample categories");

            Category food = new Category("Ruoka");
            Category rent = new Category("Vuokra");
            Category hobbies = new Category("Harrastukset");
            Category insurances = new Category("Vakuutukset");
            Category travelling = new Category("Matkustaminen");
            Category decorating = new Category("Sisustaminen");
            Category freetime = new Category("Viihde ja vapaa-aika");

            categoryRepository.save(food);
            categoryRepository.save(rent);
            categoryRepository.save(hobbies);
            categoryRepository.save(insurances);
            categoryRepository.save(travelling);
            categoryRepository.save(decorating);
            categoryRepository.save(freetime);

            log.info("Saving sample expenses");
            expenseRepository.save(new Expense("Viikon ruokaostokset", 50.0, LocalDate.of(2024, 10, 1), "Prisma", food));
            expenseRepository.save(new Expense("Vuokra", 800.0, LocalDate.of(2024, 10, 1), "-", rent));
            expenseRepository.save(new Expense("Kuntosali", 30.0, LocalDate.of(2024, 10, 2), "Elixia", hobbies));
            expenseRepository.save(new Expense("Elokuvalippu", 15.0, LocalDate.of(2024, 10, 3), "Finnkino", hobbies));
            expenseRepository.save(new Expense("Uusi sohva", 600.0, LocalDate.of(2024, 10, 4), "IKEA", decorating));
            expenseRepository.save(new Expense("Junalippu Tampereelle", 15.0, LocalDate.of(2024, 10, 5), "VR", travelling));
            expenseRepository.save(new Expense("Konserttilippu", 49.90, LocalDate.of(2024, 10, 6), "Ticketmaster", freetime));
            expenseRepository.save(new Expense("Junalippu Helsinkiin", 22.0, LocalDate.of(2024, 10, 7), "VR", travelling));
            expenseRepository.save(new Expense("Välipalaostoksia", 4.95, LocalDate.of(2024, 10, 8), "Alepa", food));
            expenseRepository.save(new Expense("Käynti uimahallissa", 5.0, LocalDate.of(2024, 10, 9), "VR", freetime));

            AppUser user1 = new AppUser("user", "$2a$10$GCNSYY8Rs9zV0YL0lainN.XBKOlq8/nB4Sf/voyCWKPzgSVupKIiW", "ROLE_USER");
            AppUser user2 = new AppUser("admin", "$2a$10$Oj9Tv75nMXoHQzwxTo1Tseuxf0jwEVkykBFQ6BfI6Ny75GAj0sOjq", "ROLE_ADMIN");

            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
    */

    @Bean
    public CommandLineRunner run(@Autowired CategoryRepository categoryRepository, @Autowired ExpenseRepository expenseRepository) {
        return (args) -> {
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
