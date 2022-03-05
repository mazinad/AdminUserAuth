package com.userAuthentication.main.controller;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.userAuthentication.main.model.PasswordReset;
import com.userAuthentication.main.model.PasswordResetToken;
import com.userAuthentication.main.model.User;
import com.userAuthentication.main.service.PasswordResetTokenService;
import com.userAuthentication.main.service.UserService;


@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {
    private final PasswordResetTokenService tokenService;
    private final MessageSource messageSource;
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ResetPasswordController(PasswordResetTokenService tokenService, MessageSource messageSource, UserService userService) {
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @GetMapping
    public String viewPage(@RequestParam(name = "token", required = false) String token,
                           Model model) {
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
        if (passwordResetToken == null) {
            logger.warn("Could not Find this token" + passwordResetToken);
            model.addAttribute("error", messageSource.getMessage("Could not find password reset token.", new Object[]{}, Locale.ENGLISH));
        } else if (passwordResetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", messageSource.getMessage("Token has expired, please request a new password reset.", new Object[]{}, Locale.ENGLISH));
        } else {
            model.addAttribute("token", passwordResetToken.getToken());
        }
        return "reset-password";
    }

    @PostMapping
    public String resetPassword(@Valid @ModelAttribute("passwordReset") PasswordReset passwordReset,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("passwordReset", passwordReset);
            return "redirect:/reset-password?token=" + passwordReset.getToken();
        }
        PasswordResetToken token = tokenService.findByToken(passwordReset.getToken());
        User user = token.getUser();
        user.setPassword(passwordReset.getPassword());
        userService.updatePassword(user);
        return "redirect:/login";
    }

    @ModelAttribute("passwordReset")
    public PasswordReset passwordReset() {
        return new PasswordReset();
    }

}
