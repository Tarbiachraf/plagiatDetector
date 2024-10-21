package ma.ecole.plagiat.repository;

import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.exceptions.StudentNotFoundException;
import ma.ecole.plagiat.exceptions.SujetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravailRepositoryTest {


    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private SujetRepository sujetRepository;

    private Sujet sujet;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        // Créer et sauvegarder un sujet pour les tests
        sujet = new Sujet();
        sujet.setId("sujet1");
        sujet.setTitre("Introduction to AI");
        sujet.setMatiere("Artificial Intelligence");
        sujet.setDescription("This subject covers the basics of AI.");
        sujetRepository.save(sujet);

        // Ajouter des travaux liés à ce sujet
        Travail travail1 = new Travail();
        travail1.setId("travail1");
        travail1.setContenu("Travail sur les bases de l'AI");
        travail1.setDateSoumission(LocalDate.now());
        travail1.setSujet(sujet);
        travailRepository.save(travail1);

        Travail travail2 = new Travail();
        travail2.setId("travail2");
        travail2.setContenu("Travail sur l'apprentissage automatique");
        travail2.setDateSoumission(LocalDate.now());
        travail2.setSujet(sujet);
        travailRepository.save(travail2);
    }
    @Test
    public void testFindBySujetId() {
        // Exécuter la méthode à tester
        List<Travail> travaux = travailRepository.findBySujetId(sujet.getId());

        // Vérifier que deux travaux sont trouvés pour ce sujet
        assertThat(travaux).hasSize(2);

        System.out.println(travaux.get(0).getContenu());
        // Vérifier le contenu des travaux
        assertThat(travaux.get(0).getContenu()).isEqualTo("Travail sur les bases de l'AI");
        assertThat(travaux.get(1).getContenu()).isEqualTo("Travail sur l'apprentissage automatique");
    }

    @Test
    void findByStudentIdAndSujetId() {
        Sujet sujet = sujetRepository.findById("670b208712b36615d03f2024")
                .orElseThrow(() -> new SujetNotFoundException("Sujet not found"));
        Student student = studentRepository.findById("670b1bf1a85be0471cf5821a")
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Travail travail = travailRepository.findBySujetAndStudent(sujet, student);
        assertThat(travail.getGrade()).isEqualTo(16);
    }
}