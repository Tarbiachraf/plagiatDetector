package ma.ecole.plagiat.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Document(collection = "travaux")
@NoArgsConstructor
@AllArgsConstructor
public class Travail implements Serializable {
    @Id
    private String id;
    private String contenu;  // Le contenu du travail soumis

    private LocalDate dateSoumission;  // Date de soumission

    @DBRef
    @ToString.Exclude
    private Student student;

    @DBRef
    @ToString.Exclude
    private Sujet sujet;

    private Double grade;
    private String status;

    public Travail(String contenu, LocalDate dateSoumission, Student student, Sujet sujet, Double grade, String status) {
        this.contenu = contenu;
        this.dateSoumission = dateSoumission;
        this.student = student;
        this.sujet = sujet;
        this.grade = grade;
        this.status = status;
    }


}
