package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.AuthRequest;
import DTO.AuthResponse;
import entity.User;
import exceptions.NotAllowedException;
import helper.JwtHelper;
import service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JwtHelper jwtHelper;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(UserService userService, JwtHelper jwtHelper,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
		super();
		this.userService = userService;
		this.jwtHelper = jwtHelper;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest body) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						body.getUsername(),
						body.getPassword()
				)
		);
		User user = userService.findByEmail(body.getUsername());
		String token = this.jwtHelper.generateToken(user);
		return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		// Encrypt Password
		String password =  user.getPassword();
		
		if(password.equals("")||password==null){
				   throw new NotAllowedException("passoword shouldn't be empty");	
		}
		
		user.setPassword(passwordEncoder.encode(password));
		
		User newUser = this.userService.create(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
