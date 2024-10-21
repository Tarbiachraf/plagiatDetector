package ma.ecole.plagiat;

import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.repository.ProfRepository;
import ma.ecole.plagiat.service.ProfService;
import ma.ecole.plagiat.service.serviceImp.PlagiarismDetectionService;
import ma.ecole.plagiat.service.serviceImp.ProfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class PlagiatApplication {


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(PlagiatApplication.class, args);

//        PlagiarismDetectionService service = new PlagiarismDetectionService();
//        String text = "running jumped easily";
//
//        String text1 = "The quick brown fox jumps over the lazy dog. The animal was agile, and it swiftly leapt over the sleeping creature with ease.";
//        String text2 = "A fast brown fox leaps across a tired dog. The nimble creature hopped swiftly, moving past the resting animal effortlessly.\n";
//        Double res = service.calculatePlagiarismPercentage(text1,text2);
//        // Get the lemmas
//        text1 = service.processText(text1);
//        List<String> lemmas = service.lemmatize(text1);
//        System.out.println(lemmas.toString());
//$
//        // Print the lemmatized words
//        System.out.println(res);  // Expected output: [run, jump, easily]


    }

}
