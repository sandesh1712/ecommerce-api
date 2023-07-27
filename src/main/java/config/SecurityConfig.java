package config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	@Autowired
	public SecurityConfig(JwtAuthFilter jwtAuthFilter,AuthenticationProvider authenticationProvider) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider =authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 http
	  .csrf().disable()
	  .cors()
	  .and()
	  .sessionManagement()
	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	  .and()
	  .authorizeHttpRequests()
	  .requestMatchers("/auth/**")
	  .permitAll()
	  .anyRequest()
	  .authenticated()
	  .and()
	  .authenticationProvider(authenticationProvider)
	  .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
		
	 return http.build();
	}
    

}
