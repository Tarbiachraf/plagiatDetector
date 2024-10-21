package ma.ecole.plagiat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {
    @Id
    private String id;  // MongoDB utilise généralement des identifiants de type String (ObjectId)
    private String name;
    private String email;
    private String studentNumber;
    private String classe;

    @DBRef
    private Prof prof;

    @CreatedDate
    private LocalDateTime createdAt;



    @LastModifiedDate
    private LocalDateTime updatedAt;


    @DBRef
    @JsonIgnore
    @ToString.Exclude
    private List<Travail> travaux;

    @DBRef
    @JsonIgnore
    private List<PlagiatDetection> plagiatDetectionList;
    public Student(String name, String email, String studentNumber, String classe, List<Travail> travaux, Prof prof) {
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.classe = classe;
        this.travaux = travaux;
        this.prof = prof;
    }


}
