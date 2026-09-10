package az.itbtechno.ecommerce.dto.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDTO {

    String name;
    String surname;
    String email;
    String password;
}