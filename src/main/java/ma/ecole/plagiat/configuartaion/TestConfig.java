package ma.ecole.plagiat.configuartaion;

import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Configuration
public class TestConfig {

//    @Bean
//    public JavaMailSender mailSender() {
//        return new JavaMailSender() {
//
//            @Override
//            public void send(SimpleMailMessage simpleMessage) throws MailException {
//                JavaMailSender.super.send(simpleMessage);
//            }
//
//            @Override
//            public void send(SimpleMailMessage... simpleMessages) throws MailException {
//
//            }
//
//            @Override
//            public MimeMessage createMimeMessage() {
//                return null; // Pas d'implémentation nécessaire
//            }
//
//            @Override
//            public MimeMessage createMimeMessage(java.io.InputStream contentStream) {
//                return null; // Pas d'implémentation nécessaire
//            }
//
//            @Override
//            public void send(MimeMessage mimeMessage) {
//                // Ne rien faire pour le test
//            }
//
//            @Override
//            public void send(MimeMessage... mimeMessages) {
//                // Ne rien faire pour le test
//            }
//
//            @Override
//            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
//                JavaMailSender.super.send(mimeMessagePreparator);
//            }
//
//            @Override
//            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
//                JavaMailSender.super.send(mimeMessagePreparators);
//            }
//        };
//    }
}
