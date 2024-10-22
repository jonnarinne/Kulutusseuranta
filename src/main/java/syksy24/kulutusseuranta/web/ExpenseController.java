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

    // Näytetään kaikki kulut
    @GetMapping("/report")
	public String showReport(Model model) {
		model.addAttribute("expenses", expenseRepository.findAll());
    	return "report";
	}

	// Ohjaus etusivulle kirjautumisen jälkeen
	@GetMapping("/home")
	public String showHome(Model model) {
    	return "home";
	}

	// Lisätään kulu
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/add")
	public String addExpense(Model model) {
		log.info("Lets go to create an expense....");
		model.addAttribute("expense", new Expense());
		model.addAttribute("categories", crepository.findAll());
		return "add";
	}

	// Tallennetaan uusi kulu
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/saveAdd")
	public String saveAdd(@ModelAttribute("expense") Expense expense) {
		expenseRepository.save(expense);
		return "redirect:report";
	}

	// Tallennetaan muokattu kulu
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveEdit")
	public String saveEdit(@ModelAttribute("expense") Expense expense) {
		expenseRepository.save(expense);
		return "redirect:report";
	}

	// Poistetaan kulu
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("delete/{id}")
	public String deleteExpense(@PathVariable("id") Long id, Model model) {
		log.info("delete expense " + id);
		expenseRepository.deleteById(id);
		return "redirect:/report";
	}

	// Muokataan kulua
	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("edit/{id}")
	public String editExpense(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editexpense", expenseRepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "edit";
	}
    
}

