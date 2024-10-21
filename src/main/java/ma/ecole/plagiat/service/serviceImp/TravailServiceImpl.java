package ma.ecole.plagiat.service.serviceImp;

import lombok.extern.slf4j.Slf4j;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.*;
import ma.ecole.plagiat.events.TravailSoumisEvent;
import ma.ecole.plagiat.exceptions.StudentNotFoundException;
import ma.ecole.plagiat.exceptions.SujetNotFoundException;
import ma.ecole.plagiat.exceptions.TravailNotFoundException;
import ma.ecole.plagiat.mappers.TravailMapper;
import ma.ecole.plagiat.reponses.TravailResponse;
import ma.ecole.plagiat.repository.StudentRepository;
import ma.ecole.plagiat.repository.SujetRepository;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.PlagiatDetectionService;
import ma.ecole.plagiat.service.TravailService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class TravailServiceImpl implements TravailService {

    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private SujetRepository sujetRepository;

    @Autowired
    private PlagiarismDetectionService plagiarismDetectionService;

    @Autowired
    private TravailMapper travailMapper;

    @Autowired
    private EmailService emailService; // Pour envoyer un email en cas de plagiat
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlagiatDetectionService plagiatDetectionService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationEventPublisher eventPublisher; // Pour publier l'événement

    @Override
    public TravailResponse createTravail(TravailDTO travailDTO, File pdfFile) throws IOException {

        log.info("Création d'un nouveau travail pour l'etudiant ID: {}", travailDTO.studentId());
        // 1. Extraire le texte du fichier PDF
        String contenuTravail = extractTextFromPDF(pdfFile);

        // 2. Créer un nouveau TravailDTO avec le contenu mis à jour
        TravailDTO updatedTravailDTO = new TravailDTO(
                contenuTravail,
                travailDTO.dateSoumission(),
                travailDTO.studentId(),
                travailDTO.sujetId(),
                travailDTO.grade(),
                travailDTO.status()
        );

        // 3. Récupérer le sujet et l'étudiant associés
        Sujet sujet = sujetRepository.findById(updatedTravailDTO.sujetId())
                .orElseThrow(() -> new SujetNotFoundException("Sujet non trouvé avec ID : " + updatedTravailDTO.sujetId()));
        Student student = studentRepository.findById(updatedTravailDTO.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Étudiant non trouvé avec ID : " + updatedTravailDTO.studentId()));

        // 4. Mapper et sauvegarder le travail
        Travail travail = travailMapper.toEntity(updatedTravailDTO);
        travail.setDateSoumission(LocalDate.now());
        travail.setSujet(sujet);
        travail.setStudent(student);
        log.debug("Contenu du travail: {}", travail.getContenu());
        // 5. Sauvegarder le travail dans la base de données
        Travail savedTravail = travailRepository.save(travail);

        log.info("Contenue du travail : {}", savedTravail.getId());
        // 6. Associer le travail au sujet et à l'étudiant
        if (sujet.getTravaux() == null) {
            sujet.setTravaux(new ArrayList<>());
        }
        if (student.getTravaux() == null) {
            student.setTravaux(new ArrayList<>());
        }
        sujet.getTravaux().add(savedTravail);
        student.getTravaux().add(savedTravail);
        sujetRepository.save(sujet);
        studentRepository.save(student);

        // 7. Publier l'événement TravailSoumisEvent pour déclencher la vérification du plagiat
        eventPublisher.publishEvent(new TravailSoumisEvent(this, savedTravail));

        // 8. Retourner la réponse
        return travailMapper.toResponse(savedTravail);
    }

    @Override
    public TravailResponse getTravailById(String id) {
        Travail travail = travailRepository.findById(id).orElseThrow(() -> new TravailNotFoundException(id));
        return travailMapper.toResponse(travail);
    }

    @Override
    public List<TravailResponse> getAllTravaux() {
        return travailRepository.findAll().stream().map(travailMapper::toResponse).toList();
    }

    @Override
    public void deleteTravailById(String id) {
        Travail travail = travailRepository.findById(id).orElseThrow(() -> new TravailNotFoundException(id));
        travailRepository.delete(travail);
    }

    // Méthode pour extraire le texte d'un fichier PDF
    private String extractTextFromPDF(File file) throws IOException {
        PDDocument document = Loader.loadPDF(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(document);
    }

    // Méthode pour envoyer un email au professeur en cas de plagiat
    private void sendPlagiarismAlert(Travail travail) {
        Prof prof = travail.getSujet().getProf();
        String email = prof.getEmail();
        String subject = "Plagiarism Detected";
        String message = "Le travail soumis par " + travail.getStudent().getName() + " pour le sujet "
                + travail.getSujet().getTitre() + " a été détecté comme étant plagié.";
        emailService.sendEmail(email, subject, message);
    }
}
