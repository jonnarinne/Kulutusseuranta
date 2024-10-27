package syksy24.kulutusseuranta.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> findByName(String name);
    List<Expense> findAll();
    List<Expense> findByCategoryName(String categoryName);
    
}
