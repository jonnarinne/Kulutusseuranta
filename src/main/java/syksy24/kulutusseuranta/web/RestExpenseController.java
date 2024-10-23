package syksy24.kulutusseuranta.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.ExpenseRepository;

@RestController
public class RestExpenseController {

    private static final Logger log = LoggerFactory.getLogger(RestExpenseController.class);

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // REST: Näytetään kaikki kulut
    @GetMapping("/expenses")
    public Iterable<Expense> getExpenses() {
        log.info("fetch expenses from db and return to client as json");
        return expenseRepository.findAll();
    }

    // REST: Näytetään kulu id:n perusteella
    @GetMapping("/expense/{id}")
    public Optional<Expense> getOneExpense(@PathVariable("id") Long expenseId) {
        log.info("fetch one expense from db and return to client as json " + expenseId);
        return expenseRepository.findById(expenseId);
    }

    // REST: Lisätään uusi kulu
    @PostMapping("/expense")
	Expense addExpense(@RequestBody Expense addExpense) {
		log.info("save a new expense " + addExpense);
		return expenseRepository.save(addExpense);
	}

    // REST: Poistetaan kulu id:n perusteella
    @DeleteMapping("/expense/{id}")
    public void deleteExpense(@PathVariable Long id) {
        log.info("Deleting expense with id = " + id);
        expenseRepository.deleteById(id);
    }


    // REST: Muokataan kirjaa id:n perusteella
    @PutMapping("/expense/{id}")
    public Expense editExpense(@RequestBody Expense editExpense, @PathVariable Long id) {
        log.info("editExpense = " + editExpense);
        log.info("edit expense, id = " + id);
        editExpense.setId(id);
        log.info("editExpense = " + editExpense);
        return expenseRepository.save(editExpense);
    }

}