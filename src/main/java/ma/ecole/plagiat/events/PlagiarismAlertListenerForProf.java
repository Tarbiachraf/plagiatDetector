package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.service.ProfService;
import ma.ecole.plagiat.service.serviceImp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlagiarismAlertListenerForProf {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ProfService profService;

    @EventListener
    public void handlePlagiarismDetectedEvent(PlagiarismDetectedEvent plagiarismDetectedEvent){
        ProfResponse prof = profService.getOurProf();
        List<Student> plagiarizedWith  = plagiarismDetectedEvent.getStudentList();
        emailService.sendEmailToProf(prof.email(), plagiarizedWith);

    }
}
