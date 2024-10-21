package ma.ecole.plagiat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Documente {
    @Id
    private String idDocument;

    private String docementContent;
    private LocalDate submissionDate;
}
