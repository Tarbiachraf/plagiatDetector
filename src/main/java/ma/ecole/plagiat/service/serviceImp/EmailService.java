package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    @Async("taskExecutor")
    public void sendEmailToProf(String email, List<Student> plagiarizedWith) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Alerte de plagiat détecté");

        // Construire le corps du message
        StringBuilder res = new StringBuilder("Cher professeur,\n\n");
        res.append("Un cas de plagiat a été détecté parmi les étudiants suivants :\n");

        // Lister les étudiants impliqués dans le plagiat
        for (Student student : plagiarizedWith) {
            res.append("- ").append(student.getName())
                    .append(" (Email: ").append(student.getEmail())
                    .append(", Numéro étudiant: ").append(student.getStudentNumber()).append(")\n");
        }

        res.append("\nVeuillez prendre les mesures appropriées.\n\nCordialement,\nL'équipe de détection de plagiat");

        // Ajouter le corps au message
        mailMessage.setText(res.toString());

        // Envoyer l'email
        mailSender.send(mailMessage);
    }


    @Async("taskExecutor")
    public void sendMailToStudent(Student student, List<Student> plagiarizedWith) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(student.getEmail());
        mailMessage.setSubject("Notification de plagiat détecté");

        // Construire le corps du message
        StringBuilder res = new StringBuilder();
        res.append("Cher ").append(student.getName()).append(",\n\n");
        res.append("Il a été détecté que votre travail est similaire à celui des étudiants suivants :\n");

        // Lister les étudiants avec qui il y a eu plagiat
        for (Student s : plagiarizedWith) {
            res.append("- ").append(s.getName())
                    .append(" (Email: ").append(s.getEmail())
                    .append(", Numéro étudiant: ").append(s.getStudentNumber()).append(")\n");
        }

        res.append("\nVeuillez contacter votre professeur pour plus de détails.\n\nCordialement,\nL'équipe de détection de plagiat");

        // Ajouter le corps au message
        mailMessage.setText(res.toString());

        // Envoyer l'email
        mailSender.send(mailMessage);
    }

}

