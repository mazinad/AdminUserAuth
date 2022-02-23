package com.userAuthentication.main.service;

import com.userAuthentication.main.model.PasswordResetToken;

public interface PasswordResetTokenService {
    PasswordResetToken findByToken(String token);
    PasswordResetToken save(PasswordResetToken passwordResetToken);
}
