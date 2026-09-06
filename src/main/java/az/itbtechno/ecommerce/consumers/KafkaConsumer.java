package az.itbtechno.ecommerce.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics="test-topic", groupId = "my-group")
    public void consumeMessage(String message){
        System.out.println("📨 Received message from Kafka: " + message);//
//        try{
//        Thread.sleep(5000);
//        }
//        catch(InterruptedException e){
//            Thread.currentThread().interrupt();
        }
    }