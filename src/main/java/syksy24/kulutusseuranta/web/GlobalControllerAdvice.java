package syksy24.kulutusseuranta.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAuthenticatedUserToModel(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            // Lisää kirjautuneen käyttäjän nimi malliin "username"-nimellä
            model.addAttribute("username", currentUser.getUsername());
        }
    }
}
