package syksy24.kulutusseuranta;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import syksy24.kulutusseuranta.web.UserDetailServiceImpl;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {


    @Autowired
    private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // Määritellään, mitkä pyynnöt ovat sallittuja
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(antMatcher("/h2-console/**")).permitAll() // H2-konsolille vapaa pääsy
            .requestMatchers("/api/admin/**").hasRole("ADMIN") // Vain adminille
            .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN") // Adminille ja käyttäjälle
            .requestMatchers("/add").authenticated() // Vain kirjautuneet käyttäjät voivat käyttää /add-polkua
            .anyRequest().authenticated() // Kaikki muut pyynnöt vaativat autentikoinnin
        )
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
        )
        .logout(logout -> logout
            .permitAll()
        )
        .csrf(csrf -> csrf
            // CSRF:n poistaminen POSTMAN-kutsuille (API testaus) tietyiltä poluilta
            .ignoringRequestMatchers("/expense/**") // Ei CSRF-suojausta näille poluille
        )
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.disable()) // H2-konsoli iframe-ongelman korjaus
        );

    return http.build();
}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	}
