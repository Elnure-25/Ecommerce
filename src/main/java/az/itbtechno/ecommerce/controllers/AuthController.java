package az.itbtechno.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("/login")
    public String login(){
        return "auth/login.html";
    }


}
