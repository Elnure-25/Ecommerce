package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.auth.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("/login")
    public String login(){
        return "auth/login.html";
    }


}
