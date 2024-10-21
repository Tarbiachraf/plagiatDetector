package ma.ecole.plagiat.service.serviceImp;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import ma.ecole.plagiat.configuartaion.NLPConfig;
import ma.ecole.plagiat.entities.Travail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Import(NLPConfig.class) // Charger la configuration pour les tests
public class PlagiarismDetectionService {

    @Autowired
    private StanfordCoreNLP pipeline;

    @Value("${plagiarism.threshold}")
    private double threshold;


    public List<String> lemmatize(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        List<String> lemmas = new ArrayList<>();
        for (CoreLabel token : document.tokens()) {
            lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
        }
        return lemmas;
    }

    public double calculateCosineSimilarity(List<String> lemmas1, List<String> lemmas2) {
        // Créer un ensemble de tous les mots (lemmas) uniques dans les deux textes
        Set<String> allWords = new HashSet<>(lemmas1);
        allWords.addAll(lemmas2);

        // Créer des vecteurs de fréquence des mots pour chaque texte
        Map<String, Integer> frequencyVector1 = getWordFrequencyVector(lemmas1, allWords);
        Map<String, Integer> frequencyVector2 = getWordFrequencyVector(lemmas2, allWords);

        // Calculer le produit scalaire des deux vecteurs
        int dotProduct = calculateDotProduct(frequencyVector1, frequencyVector2);

        // Calculer les normes (magnitudes) des vecteurs
        double magnitude1 = calculateMagnitude(frequencyVector1);
        double magnitude2 = calculateMagnitude(frequencyVector2);

        // Calcul de la similarité cosinus
        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;  // Si l'un des textes est vide, pas de similarité
        }

        return dotProduct / (magnitude1 * magnitude2);
    }

    // Méthode pour détecter si un texte est un plagiat
    /*public boolean detectPlagiarism(String newText, List<String> existingTexts) {
        // Lemmatisation du nouveau texte soumis
        List<String> lemmasNewText = lemmatize(newText);

        // Pour chaque texte existant, on vérifie la similarité
        for (String existingText : existingTexts) {
            List<String> lemmasExistingText = lemmatize(existingText);
            double similarityScore = calculateCosineSimilarity(lemmasNewText, lemmasExistingText);

            // Si le score de similarité dépasse le seuil, on considère qu'il y a plagiat
            if (similarityScore >= threshold) {
                return true; // Plagiat détecté
            }
        }

        return false; // Pas de plagiat détecté
    }*/
    public List<Travail> detectPlagiarism(String newText, List<Travail> existingTravaux) {
        List<Travail> plagiarizedTravaux = new ArrayList<>();

        // Lemmatisation du nouveau texte soumis
        List<String> lemmasNewText = lemmatize(newText);

        // Pour chaque texte existant, on vérifie la similarité
        for (Travail existingTravail : existingTravaux) {
            List<String> lemmasExistingText = lemmatize(existingTravail.getContenu());
            double similarityScore = calculateCosineSimilarity(lemmasNewText, lemmasExistingText);

            // Si le score de similarité dépasse le seuil, on ajoute le travail existant à la liste
            if (similarityScore >= threshold) {
                plagiarizedTravaux.add(existingTravail);
            }
        }

        return plagiarizedTravaux; // Retourner la liste des travaux plagiés
    }


    // Méthode pour créer un vecteur de fréquence des mots (lemmes)
    private Map<String, Integer> getWordFrequencyVector(List<String> lemmas, Set<String> allWords) {
        Map<String, Integer> frequencyVector = new HashMap<>();
        for (String word : allWords) {
            frequencyVector.put(word, Collections.frequency(lemmas, word));
        }
        return frequencyVector;
    }

    // Méthode pour calculer le produit scalaire de deux vecteurs
    private int calculateDotProduct(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        int dotProduct = 0;
        for (String key : vector1.keySet()) {
            dotProduct += vector1.get(key) * vector2.getOrDefault(key, 0);
        }
        return dotProduct;
    }

    // Méthode pour calculer la norme d'un vecteur (magnitude)
    private double calculateMagnitude(Map<String, Integer> frequencyVector) {
        int sumOfSquares = frequencyVector.values().stream().mapToInt(value -> value * value).sum();
        return Math.sqrt(sumOfSquares);
    }
}
