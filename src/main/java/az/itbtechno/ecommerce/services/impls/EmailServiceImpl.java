package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    @Override
    public boolean sendConfirmationEmail(String email,String token){
        try{

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
            helper.setTo(email);
            helper.setSubject("Qeydiyyat");
            helper.setText("<a href='http://localhost:8080/user/confirm?email="+email+"&token="+token+"'<h1> Hesabi tesdiqle </h1>",true);
            emailSender.send(message);


            emailSender.send(message);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}

