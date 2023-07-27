package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import exceptions.UnauthorizedException;
import repository.UserRepository;

@Configuration
public class ApplicationConfig {
	private UserRepository userRepository;
	
	@Autowired
	public ApplicationConfig(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return username -> {
		    	return this.userRepository.findByEmail(username).
		    		orElseThrow(() -> new UnauthorizedException("Unauthorized"));
		};
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() { 
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
