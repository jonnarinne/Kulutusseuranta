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
        .authorizeHttpRequests(authorize -> authorize
			.requestMatchers(antMatcher("/h2-console/**")).permitAll()
            .requestMatchers("/css/**").permitAll() 
			.requestMatchers("/signup").permitAll() 
			.requestMatchers("/saveuser").permitAll()
			.requestMatchers("/add").hasAnyRole("USER", "ADMIN") 
			.requestMatchers("/report").hasAnyRole("USER", "ADMIN") 
			.requestMatchers("/home").authenticated() 
			.requestMatchers("/edit/**").hasRole("ADMIN") 
			.requestMatchers("/delete/**").hasRole("ADMIN") 
			.anyRequest().authenticated() 
        )
        .formLogin(form -> form
            .loginPage("/login")
			.defaultSuccessUrl("/home", true)
            .permitAll()
        )
        .httpBasic(httpBasic -> {})
        .logout(logout -> logout
            .permitAll()
        )
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/expense/**") 
        )
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.disable()) 
        );

    return http.build();
}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	}
