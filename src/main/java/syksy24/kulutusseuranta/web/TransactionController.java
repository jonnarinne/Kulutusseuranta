package syksy24.kulutusseuranta.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syksy24.kulutusseuranta.domain.Transaction;
import syksy24.kulutusseuranta.domain.Category;


@Controller
public class TransactionController {

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

    
    
}
