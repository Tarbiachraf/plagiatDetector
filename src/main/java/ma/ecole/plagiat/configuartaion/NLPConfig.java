package ma.ecole.plagiat.configuartaion;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class NLPConfig {
    @Bean
    public StanfordCoreNLP stanfordCoreNLP(){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        return new StanfordCoreNLP(props);
    }
}
