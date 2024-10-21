package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.service.ProfService;
import ma.ecole.plagiat.service.serviceImp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TravailEventListener {
    @Autowired
    private ProfService profService;
    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleTravailSoumisEvent(TravailSoumisEvent event){
        ProfResponse profResponse = profService.getOurProf();

        // Envoyer un email au professeur
        String subject = "Nouveau travail soumis";
        String message = "Un nouveau travail a été soumis pour votre sujet.";
        emailService.sendEmail(profResponse.email(), subject, message);

        System.out.println("Notification envoyée au prof : " + profResponse.name());

    }
}
