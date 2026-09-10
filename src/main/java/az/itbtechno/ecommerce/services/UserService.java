package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.auth.RegisterDTO;

public interface UserService {

    void registerUser(RegisterDTO registerDTO);

    boolean confirmUser(String email, String token);
}