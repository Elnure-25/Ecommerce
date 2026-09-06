package az.itbtechno.ecommerce.consumers;

import az.itbtechno.ecommerce.dto.auth.ConfirmDTO;
import az.itbtechno.ecommerce.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics="email-topic", groupId = "my-group")
    public void consumeMessage(String message){
        try{
        Thread.sleep(10000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        ConfirmDTO confirmDTO = objectMapper.readValue(message, ConfirmDTO.class);

        System.out.println("📨 Received message from Kafka: " + message);//
emailService.sendConfirmationEmail(confirmDTO.getEmail(), confirmDTO.getToken());
    }

    }