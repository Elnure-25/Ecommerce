package az.itbtechno.ecommerce.services;

public interface EmailService {
    boolean sendConfirmationEmail(String email,String token);
}
