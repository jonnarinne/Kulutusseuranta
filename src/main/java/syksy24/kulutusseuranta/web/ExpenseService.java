package syksy24.kulutusseuranta.web;

import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByName(String name) {
        return expenseRepository.findByName(name);
    }

    public List<Expense> getExpensesByCategory(String categoryName) {
        return expenseRepository.findByCategoryName(categoryName);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
