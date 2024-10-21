package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.reponses.SujetResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SujetMapperTest {

    @InjectMocks
    private SujetMapper sujetMapper;
    @Mock
    private ProfRepository profRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toResponse() {
        // Arrange
        Sujet sujet = new Sujet("Mathématiques", "Algèbre", "Introduction à l'algèbre", null, Collections.emptyList());
        sujet.setId("1");
        // Act
        SujetResponse response = sujetMapper.toResponse(sujet);

        // Assert
        assertEquals(sujet.getId(), response.id());
        assertEquals(sujet.getTitre(), response.titre());
        assertEquals(sujet.getMatiere(), response.matiere());
        assertEquals(sujet.getDescription(), response.description());
        assertNull(response.travaux());  // Vérifier que les travaux sont nuls si vide
    }

    @Test
    void toEntity() {
        // Arrange
        Prof prof = new Prof("Professeur X", "prof@example.com", "Informatique", Collections.emptyList(), Collections.emptyList());
        prof.setId("1");
        when(profRepository.findAll()).thenReturn(Collections.singletonList(prof));  // Simuler la récupération d'un Prof

        SujetDTO sujetDTO = new SujetDTO("Mathématiques", "Algèbre", "Introduction à l'algèbre");

        // Act
        Sujet sujet = sujetMapper.toEntity(sujetDTO);

        // Assert
        assertEquals(sujetDTO.titre(), sujet.getTitre());
        assertEquals(sujetDTO.matiere(), sujet.getMatiere());
        assertEquals(sujetDTO.description(), sujet.getDescription());
        assertEquals(prof, sujet.getProf());  // Le prof doit être celui renvoyé par le mock
    }
}