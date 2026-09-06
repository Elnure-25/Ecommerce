package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.auth.ConfirmDTO;
import az.itbtechno.ecommerce.dto.request.user.RegisterDto;
import az.itbtechno.ecommerce.models.User;
import az.itbtechno.ecommerce.repostories.UserRepository;
import az.itbtechno.ecommerce.services.EmailService;
import az.itbtechno.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;


    private static final String TOPIC = "email-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void registerUser(RegisterDto registerDto) {


        User findUser = userRepository.findByEmail(registerDto.getEmail());

        if (findUser == null) {

            User user = new User();

            user.setName(registerDto.getName());
            user.setSurname(registerDto.getSurname());
            user.setEmail(registerDto.getEmail());

            String token = UUID.randomUUID().toString().replace("-", "")+UUID.randomUUID().toString().replace("-", "");
            String password = passwordEncoder.encode(registerDto.getPassword());
            user.setPassword(password);

            // User account status
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);



            ConfirmDTO confirmMessage = new ConfirmDTO(registerDto.getEmail(),token);
            String message= objectMapper.writeValueAsString(confirmMessage);


            kafkaTemplate.send(TOPIC, message);

            userRepository.save(user);
        }
    }
}