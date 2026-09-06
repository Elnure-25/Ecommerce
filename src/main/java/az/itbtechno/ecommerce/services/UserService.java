package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.request.user.RegisterDto;

public interface UserService {

    void registerUser(RegisterDto registerDto);
}