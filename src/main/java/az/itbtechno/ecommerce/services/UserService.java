package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.auth.RegisterDTO;
import az.itbtechno.ecommerce.models.User;

public interface UserService {

    void registerUser(RegisterDTO registerDTO);

    boolean confirmUser(String email, String token);


    User findUserByEmail(String email);
}