package com.userAuthentication.main.service;



import org.springframework.security.core.userdetails.UserDetailsService;

import com.userAuthentication.main.model.User;
import com.userAuthentication.main.webData.UserRegistrationDto;





public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	void updatePassword(User user);
	User findByEmail(String email);
	User getUserById(long id);
}
