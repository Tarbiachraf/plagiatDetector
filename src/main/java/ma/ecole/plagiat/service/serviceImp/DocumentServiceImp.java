package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.entities.Documente;
import ma.ecole.plagiat.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImp {
    @Autowired
    private DocumentRepository documentRepository;
    public String processText(String text){
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "");
        return text.trim();
    }
    public double calculateCosineSimilarity(String text1, String text2){
        Map<String, Integer> wordFreq1 = getWordFrequency(text1);
        Map<String, Integer> wordFreq2 = getWordFrequency(text2);
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for(String key : wordFreq1.keySet()){
            dotProduct += wordFreq1.get(key) * wordFreq2.getOrDefault(key, 0);
            normA += Math.pow(wordFreq1.get(key), 2);
        }
        for (int value : wordFreq2.values()) {
            normB += Math.pow(value, 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private Map<String, Integer> getWordFrequency(String text) {
        Map<String, Integer> map = new HashMap<>();
        String[] words = text.split("//s+");
        for (String word: words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        return map;
    }
//    public boolean checkPlagiarism(String newDocText){
//        List<Documente> existingDocs = documentRepository.findAll();
//        for(Documente documente : existingDocs){
//            double similarity = calculateCosineSimilarity(newDocText, documente.getDocementContent());
//            if (similarity > 0.8) { // Seuil de 80 % pour détecter le plagiat
//                return true;
//            }
//        }
//        return false;
//    }
}
