package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.PlagiatDetectionService;
import ma.ecole.plagiat.service.serviceImp.PlagiarismCheckService;
import ma.ecole.plagiat.service.serviceImp.PlagiarismDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlagiarismCheckListener {
    @Autowired
    private TravailRepository travailRepository;
    @Autowired
    private PlagiarismDetectionService plagiarismDetectionService;

    @Autowired
    private PlagiatDetectionService plagiatDetectionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @EventListener
    public void handleTravailSubmittedEvent(TravailSoumisEvent event) {
        Travail travail = event.getTravail();

        // Récupérer les travaux existants pour le même sujet
        List<Travail> travauxExistants = travailRepository.findBySujetId(travail.getSujet().getId());

        // Vérifier le plagiat
        List<Travail> plagiarizedTravaux = plagiarismDetectionService.detectPlagiarism(travail.getContenu(), travauxExistants);

        if (!plagiarizedTravaux.isEmpty()) {
            PlagiarismCheckService.dd(travail,travail.getStudent(),travail.getSujet(),plagiarizedTravaux,plagiatDetectionService);
            // Publier un nouvel événement de plagiat détecté
            eventPublisher.publishEvent(new PlagiarismDetectedEvent(this, travail,
                    plagiarizedTravaux.stream().map(Travail::getStudent).collect(Collectors.toList())));
        }
    }
}
