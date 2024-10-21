package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.PlagiatDetection;
import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.repository.StudentRepository;
import ma.ecole.plagiat.repository.SujetRepository;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.PlagiatDetectionService;
import ma.ecole.plagiat.service.StudentService;
import ma.ecole.plagiat.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlagiarismCheckService {

    @Autowired
    private TravailRepository travailRepository;
    @Autowired
    private PlagiarismDetectionService plagiarismDetectionService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PlagiatDetectionService plagiatDetectionService;
    @Autowired
    private SujetRepository sujetRepository;

    @Scheduled(cron = "0 0 * * * ?")
    public void checkForPlagiarismInAllTravaux(){
        List<Travail> travailList = travailRepository.findAll();

        for (Travail travail : travailList){
            Student student = travail.getStudent();
            Sujet sujet = travail.getSujet();
            List<Travail> travails = travailRepository.findBySujetId(travail.getSujet().getId());
            travails.remove(travail);
            List<Travail> plagiarizedTravaux = plagiarismDetectionService.detectPlagiarism(travail.getContenu(), travails);
            if(!plagiarizedTravaux.isEmpty() && !"Plagiarized".equals(travail.getStatus())){
                dd(travail, student, sujet, plagiarizedTravaux, plagiatDetectionService);
                studentRepository.save(student);
                sujetRepository.save(sujet);
                travailRepository.save(travail);
            }
        }

    }

    public static void dd(Travail travail, Student student, Sujet sujet, List<Travail> plagiarizedTravaux, PlagiatDetectionService plagiatDetectionService) {
        travail.setStatus("Plagiarized");

        PlagiatDetection plagiatDetection = new PlagiatDetection();
        plagiatDetection.setStudent(student);
        plagiatDetection.setSujet(sujet);
        plagiatDetection.setPlagiarizesWith(plagiarizedTravaux.
                stream().
                map(travail1 -> travail1.getStudent()).
                collect(Collectors.toList())
        );

        PlagiatDetection plagiatDetection1 =  plagiatDetectionService.createPlagiat(plagiatDetection);
        if(student.getPlagiatDetectionList() == null){
            student.setPlagiatDetectionList(new ArrayList<>());
        }
        if(sujet.getPlagiatDetectionList() == null){
            sujet.setPlagiatDetectionList(new ArrayList<>());
        }

        student.getPlagiatDetectionList().add(plagiatDetection1);
        sujet.getPlagiatDetectionList().add(plagiatDetection1);
    }
}
