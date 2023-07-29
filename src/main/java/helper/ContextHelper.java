package helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import entity.User;

@Component
public class ContextHelper {
	private Authentication authentication;

	public ContextHelper() {
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
	}
	
	public User CurrentUser() { 
		return (User)this.authentication.getDetails();
	}
}
