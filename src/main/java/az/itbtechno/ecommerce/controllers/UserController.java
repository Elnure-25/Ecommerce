package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.auth.RegisterDTO;
import az.itbtechno.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return "redirect:/login";
    }

    @GetMapping("/user/confirm")
    public String confirm(String email, String token) {
        boolean confirmUser = userService.confirmUser(email, token);
        System.out.println("Salam");
        return "redirect:/login";
    }
}