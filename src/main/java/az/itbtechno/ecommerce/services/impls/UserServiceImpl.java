package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.models.User;
import az.itbtechno.ecommerce.repostories.UserRepository;
import az.itbtechno.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import az.itbtechno.ecommerce.dto.request.user.RegisterDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;

    @Override
    public void registerUser(RegisterDto registerDto){
        User findUser = userRepository.findByEmail(registerDto.getEmail());
        if(findUser==null){
            User user = new User();
            user.setName(registerDto.getName());
            user.setSurname(registerDto.getSurname());
            user.setEmail(registerDto.getEmail());
            String password = passwordEncoder.encode(registerDto.getPassword());
            user.setPassword(password);
            user.setEnabled(false);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("info@itbrains.edu.az");
            message.setSubject("Qeydiyyat");
            message.setTo(registerDto.getEmail());
            message.setText("Qeydiyyatdan kecdiniz");

            emailSender.send(message);


            userRepository.save(user);
        }

    }
}
