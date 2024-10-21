package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import ma.ecole.plagiat.service.ProfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfServiceImplTest {

    @Autowired
    private ProfService profService;

    @Autowired
    private ProfRepository profRepository;

    @BeforeEach
    public void setUp() {
        //profRepository.deleteAll();  // Nettoyer la base avant chaque test
    }

    @Test
    void createOrUpdateProf() {
        ProfDTO prof = new ProfDTO("Mohamed El yaakoubiiii","tarbi.achraf@gmail.com","informatique");
        ProfResponse profResponse = profService.createOrUpdateProf(prof);
        System.out.println(profResponse.email());
        assertNotNull(profResponse);

        Prof prof1 = new Prof();

    }

//    @Test
//    public void testCreateProf() {
//        // Créer un nouveau professeur
//        Prof prof = new Prof();
//        prof.setName("John Doe");
//        prof.setEmail("john.doe@example.com");
//
//        // Appeler le service pour créer le professeur
//        Prof createdProf = profService.createProf(prof);
//
//        // Vérifier que le professeur a été créé avec succès
//        assertNotNull(createdProf.getId());  // Vérifier que l'ID est généré
//        assertEquals("John Doe", createdProf.getName());
//        assertEquals("john.doe@example.com", createdProf.getEmail());
//        System.out.println(createdProf.getId());
//
//        // Vérifier que le professeur est bien dans la base de données
//        Prof foundProf = profRepository.findById(createdProf.getId()).orElse(null);
//        assertNotNull(foundProf);
//        assertEquals("John Doe", foundProf.getName());
//    }
}