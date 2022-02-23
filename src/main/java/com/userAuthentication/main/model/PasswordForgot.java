package com.userAuthentication.main.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgot {
    @NotEmpty(message = "{EMAIL_REQUIRED}")
    @Email(message = "{NOT_VALID_EMAIL}")
    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
