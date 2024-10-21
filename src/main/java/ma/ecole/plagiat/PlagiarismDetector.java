package ma.ecole.plagiat;


import java.util.HashSet;
import java.util.Set;

public class PlagiarismDetector {

    // Méthode pour générer les n-grammes d'un texte
    public Set<String> generateNGrams(String text, int n) {
        String[] words = text.split("\\s+");
        Set<String> ngrams = new HashSet<>();

        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder ngram = new StringBuilder();
            for (int j = i; j < i + n; j++) {
                ngram.append(words[j]).append(" ");
            }
            ngrams.add(ngram.toString().trim());
        }
        return ngrams;
    }

    // Méthode pour calculer le Jaccard Index entre deux ensembles d'n-grammes
    public double calculateJaccardIndex(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }

    // Détecter le plagiat entre deux textes
    public boolean isPlagiarized(String text1, String text2, double similarityThreshold, int nGramSize) {
        Set<String> ngramsText1 = generateNGrams(text1, nGramSize);
        Set<String> ngramsText2 = generateNGrams(text2, nGramSize);

        double similarity = calculateJaccardIndex(ngramsText1, ngramsText2);
        System.out.println(similarity);
        return similarity >= similarityThreshold;
    }

    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector();

        String text1 = "This is a sample document used for plagiarism detection";
        String text2 = "This is a sample document for detecting plagiarism";

        boolean isPlagiarized = detector.isPlagiarized(text1, text2, 0.5, 3); // n-grammes de taille 3

        if (isPlagiarized) {
            System.out.println("Plagiarism detected!");
        } else {
            System.out.println("No plagiarism detected.");
        }
    }
}
