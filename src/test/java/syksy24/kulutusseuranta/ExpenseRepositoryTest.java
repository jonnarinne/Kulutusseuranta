package syksy24.kulutusseuranta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.AppUser;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.ExpenseRepository;
import syksy24.kulutusseuranta.domain.AppUserRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private CategoryRepository crepository;

    @Autowired
    private AppUserRepository appuserRepository;

    // Testi, joka tarkistaa kulun lisäämisen tietokantaan
    @Test
    public void createNewExpense() {
    // Luo tai hae testikäyttäjä
    AppUser testUser = new AppUser("testuser", "hashedPassword", "USER");
    appuserRepository.save(testUser); // Tallenna käyttäjä tietokantaan, jos ei ole vielä olemassa

    LocalDate date = LocalDate.parse("2024-10-01");

    // Luo uusi Expense, liitä testikäyttäjä
    Expense expense = new Expense("Junalippu", 50.0, date, "VR", crepository.findByName("Matkustaminen").get(0), testUser);
    expenseRepository.save(expense);
    
    // Varmistetaan, että kululle on luotu id
    assertThat(expense.getId()).isNotNull();
    
    // Varmista, että kulu liittyy oikeaan käyttäjään
    assertThat(expense.getAppuser()).isEqualTo(testUser);
}


    // Testi, joka tarkistaa kulun noutamisen tietokannasta kulun nimen perusteella
    @Test
    public void findByNameShouldReturnExpense() {
        List<Expense> expenses = expenseRepository.findByName("Konserttilippu");

        assertThat(expenses).hasSize(1);
        assertThat(expenses.get(0).getName()).isEqualTo("Konserttilippu");
    }
    
    // Testi, joka tarkistaa kulun noutamisen kategorian nimen perusteella
    @Test
    public void findByCategoryNameShouldReturnExpense() {
        List<Expense> expenses = expenseRepository.findByCategoryName("Ruoka");

        assertThat(expenses).isNotEmpty();
        assertThat(expenses.get(0).getCategory().getName()).isEqualTo("Ruoka");
    }


    // Testi, joka muokkaa kulun tietoja
    @Test
    public void updateExpenseAmount() {
        List<Expense> expenses = expenseRepository.findByName("Vuokra");
        Expense expense = expenses.get(0);
        expense.setAmount(750.0);
        expenseRepository.save(expense);

        List<Expense> updatedExpenses = expenseRepository.findByName("Vuokra");
        assertThat(updatedExpenses).hasSize(1);
        assertThat(updatedExpenses.get(0).getAmount()).isEqualTo(750.0);
    }
}
