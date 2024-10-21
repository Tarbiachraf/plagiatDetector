package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.service.PlagiatDetectionService;
import ma.ecole.plagiat.service.serviceImp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlagiarismAlertListenerForStudent {
    @Autowired
    private EmailService emailService;

    @Autowired
    private PlagiatDetectionService plagiatDetectionService;

    @EventListener
    public void handlePlagiarismDetectedEvent(PlagiarismDetectedEvent event){
        Travail travail = event.getTravail();
        List<Student> plagiarizedWith  = event.getStudentList();

        emailService.sendMailToStudent(travail.getStudent(), plagiarizedWith);

    }
}
