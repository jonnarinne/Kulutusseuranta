package syksy24.kulutusseuranta.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> findByName(String name);
    List<Expense> findAll();
    List<Expense> findByCategoryName(String categoryName);
    
    @Query("SELECT e FROM Expense e WHERE e.appuser.id = :userId")
    List<Expense> findByAppUserId(@Param("userId") Long userId);
}
