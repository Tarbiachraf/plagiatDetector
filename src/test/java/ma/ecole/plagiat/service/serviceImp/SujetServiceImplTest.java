package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.reponses.SujetResponse;
import ma.ecole.plagiat.service.SujetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SujetServiceImplTest {

    @Autowired
    private SujetService sujetService;

    @Test
    void createSujet() {
        SujetDTO sujetDTO = new SujetDTO("Sujet Test", "Description du sujet test", "Categorie Test");

        SujetResponse sujetResponse = sujetService.createSujet(sujetDTO);

        assertNotNull(sujetResponse);
        assertEquals("Sujet Test", sujetResponse.titre());
    }
}