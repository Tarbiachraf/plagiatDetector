package ma.ecole.plagiat.service.serviceImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentServiceImpTest {


    @Autowired
    public DocumentServiceImp documentServiceImp;
    @Test
    void calculateCosineSimilarity() {
        String text1 = "hello world";
        String text2 = "hello world";
        double expected = 1.0;
        double actual = documentServiceImp.calculateCosineSimilarity(text1,text2);
        assertEquals(expected,actual,0.01);

        text1 = "hello world";
        text2 = "goodbye world";
        expected = 0.0; // La similarité cosinus entre des textes complètement différents est 0.0
        actual = documentServiceImp.calculateCosineSimilarity(text1, text2);
        assertEquals(expected, actual, 0.001);

        text1 = "hello world";
        text2 = "hello everyone";
        // Similarité cosinus attendue calculée manuellement ou à l'aide d'un autre outil
        // Ici, pour l'exemple, on suppose qu'elle est approximativement 0.707
        expected = 0.707;
        actual = documentServiceImp.calculateCosineSimilarity(text1, text2);
        assertEquals(expected, actual, 0.001);

        // Cas avec des textes vides
        text1 = "";
        text2 = "";
        expected = 1.0; // Deux textes vides sont considérés comme identiques
        actual = documentServiceImp.calculateCosineSimilarity(text1, text2);
        assertEquals(expected, actual, 0.001);
    }
}