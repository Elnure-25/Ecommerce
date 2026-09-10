package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.auth.ConfirmDTO;
import az.itbtechno.ecommerce.dto.auth.RegisterDTO;
import az.itbtechno.ecommerce.models.User;
import az.itbtechno.ecommerce.repostories.UserRepository;
import az.itbtechno.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "email-topic";

    @Override
    public void registerUser(RegisterDTO registerDTO) {

        User findUser = userRepository.findByEmail(registerDTO.getEmail());

        if (findUser == null) {

            User user = new User();

            user.setName(registerDTO.getName());
            user.setSurname(registerDTO.getSurname());
            user.setEmail(registerDTO.getEmail());

            String token = UUID.randomUUID().toString().replace("-", "")
                    + UUID.randomUUID().toString().replace("-", "");

            user.setConfirmationToken(token);

            String password = passwordEncoder.encode(registerDTO.getPassword());
            user.setPassword(password);

            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(false);

            ConfirmDTO confirmMessage =
                    new ConfirmDTO(registerDTO.getEmail(), token);

            String message = objectMapper.writeValueAsString(confirmMessage);

            userRepository.save(user);

            kafkaTemplate.send(TOPIC, message);
        }
    }

    @Override
    public boolean confirmUser(String email, String token) {

        User findUser = userRepository.findByEmail(email);

        if (findUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (findUser.getConfirmationToken().equals(token)) {

            findUser.setEnabled(true);
            findUser.setAccountNonExpired(true);
            findUser.setAccountNonLocked(true);
            findUser.setCredentialsNonExpired(true);

            userRepository.save(findUser);

            return true;
        }

        return false;
    }
}