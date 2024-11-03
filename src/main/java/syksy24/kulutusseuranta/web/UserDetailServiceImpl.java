package syksy24.kulutusseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import syksy24.kulutusseuranta.domain.AppUser;
import syksy24.kulutusseuranta.domain.AppUserRepository;
import syksy24.kulutusseuranta.domain.Expense;
import syksy24.kulutusseuranta.domain.ExpenseRepository;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new org.springframework.security.core.userdetails.User(
            username,
            curruser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(curruser.getRole())
        );
    }

    // Metodi hakee kirjautuneen käyttäjän kulut
    public List<Expense> getExpensesForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser currentUser = repository.findByUsername(username);
        return expenseRepository.findByAppUserId(currentUser.getId());
    }
}
