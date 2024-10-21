package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.configuartaion.TestConfig;
import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.TravailResponse;
import ma.ecole.plagiat.repository.TravailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravailServiceImplTest {
    @Autowired
    private TravailServiceImpl travailService;

    @Autowired
    private TravailRepository travailRepository;


    @Test
    void createTravail() throws IOException {
        TravailDTO travailDTO = new TravailDTO(
                "Contenu initiale",
                LocalDate.now(),
                "670b1c111b80003ab3731097",
                "670b208712b36615d03f2024",
                16.0,
                "Pending"
        );
        File pdfFile = new ClassPathResource("TD2_TARBI.pdf").getFile();
        //C:/Users/dell/Downloads/bahnschrift/BAHNSCHRIFT 1.TTF
        TravailResponse result = travailService.createTravail(travailDTO,pdfFile);

        assertNotNull(result);
        assertNotNull(result.id());

 //       List<Travail> savedTravaux = travailRepository.findAll();
 //       assertEquals(1, savedTravaux.size());
//        assertEquals("Submitted", savedTravaux.get(0).getStatus());

    }

}