package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.reponses.simpleResponse.SimpleSujetResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSujetMapperTest {

    @Test
    void toSimpleResponse() {
        Sujet sujet = new Sujet("1","Mathématiques", "Algèbre", "Introduction à l'algèbre", null, Collections.emptyList(),Collections.emptyList());

        // Act: Mapper l'objet Sujet en SimpleSujetResponse
        SimpleSujetResponse response = SimpleSujetMapper.toSimpleResponse(sujet);

        // Assert: Vérifier que les champs sont correctement mappés
        assertEquals(sujet.getId(), response.id());
        assertEquals(sujet.getTitre(), response.titre());
        assertEquals(sujet.getMatiere(), response.matiere());
        assertEquals(sujet.getDescription(), response.description());
    }
}