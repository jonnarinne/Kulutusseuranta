package syksy24.kulutusseuranta.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.Category;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.ExpenseRepository;


@Controller
public class ExpenseController {

    @Autowired
	private CategoryRepository crepository; 

	@Autowired
	private ExpenseRepository expenseRepository;

	private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

    
    
}
