package syksy24.kulutusseuranta.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.validation.BindingResult;

import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.CategoryRepository;
import syksy24.kulutusseuranta.domain.ExpenseRepository;
import syksy24.kulutusseuranta.domain.AppUser;
import syksy24.kulutusseuranta.domain.AppUserRepository;
import syksy24.kulutusseuranta.web.UserDetailServiceImpl;

@Controller
public class ExpenseController {

    @Autowired
	private CategoryRepository crepository; 

	@Autowired
	private ExpenseRepository expenseRepository;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private AppUserRepository appUserRepository;

	private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

    // Näytetään kirjautuneen käyttäjän kulut
	@GetMapping("/report")
	public String showUserReport(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    AppUser appUser = appUserRepository.findByUsername(currentUser.getUsername());
    model.addAttribute("expenses", expenseRepository.findByAppUserId(appUser.getId()));
    	return "report";
	}


	// Ohjaus etusivulle kirjautumisen jälkeen
	@GetMapping("/home")
	public String showHome() {
    	return "home";
	}

	// Lisätään uusi kulu
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/add")
	public String addExpense(Model model) {
		log.info("Lets go to create an expense....");
		model.addAttribute("expense", new Expense());
		model.addAttribute("categories", crepository.findAll());
		return "add";
	}

	// Tallennetaan uusi kulu kirjautuneen käyttäjän tiedoilla
	@PostMapping("/saveAdd")
	public String saveAdd(@Valid @ModelAttribute("expense") Expense expense, BindingResult bindingResult, Model model,
	                      @AuthenticationPrincipal UserDetails currentUser) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("categories", crepository.findAll());
	        return "add"; 
	    }
	    
	    // Hakee kirjautuneen käyttäjän
	    AppUser appUser = appUserRepository.findByUsername(currentUser.getUsername());
	    expense.setAppuser(appUser);  // Asettaa kulun omistajaksi kirjautuneen käyttäjän
	    expenseRepository.save(expense);
	    return "redirect:report";
	}

	// Tallennetaan muokattu kulu (vain ADMIN)
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveEdit")
	public String saveEdit(@Valid @ModelAttribute("expense") Expense expense, BindingResult bindingResult, Model model ) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("categories", crepository.findAll());
	        return "edit";
	    }
	    expenseRepository.save(expense);
	    return "redirect:report";
	}

	// Poistetaan kulu (vain ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("delete/{id}")
	public String deleteExpense(@PathVariable("id") Long id, Model model) {
		log.info("delete expense " + id);
		expenseRepository.deleteById(id);
		return "redirect:/report";
	}

	// Muokataan kulua (vain ADMIN)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("edit/{id}")
	public String editExpense(@PathVariable("id") Long id, Model model) {
    	Optional<Expense> optionalExpense = expenseRepository.findById(id); 
    	if (optionalExpense.isPresent()) {
        	model.addAttribute("expense", optionalExpense.get());
    	} else {
        	return "redirect:/report";
    	}
    	model.addAttribute("categories", crepository.findAll()); 
    	return "edit";
	}
}

