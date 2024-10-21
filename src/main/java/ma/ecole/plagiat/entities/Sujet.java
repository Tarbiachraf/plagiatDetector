package ma.ecole.plagiat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "sujets")
@AllArgsConstructor
@NoArgsConstructor
public class Sujet implements Serializable {
    @Id
    private String id;
    private String titre;
    private String matiere;
    private String description;


    @DBRef
    @JsonIgnore
    private Prof prof;  // Référence au professeur qui a créé le sujet
    @DBRef
    @JsonIgnore
    private List<Travail> travaux;  // Les travaux soumis pour ce sujet

    @DBRef
    @JsonIgnore
    private List<PlagiatDetection> plagiatDetectionList;



    public Sujet(String id, String titre, String matiere, String description, Prof prof) {
        this.id = id;
        this.titre = titre;
        this.matiere = matiere;
        this.description = description;
        this.prof = prof;
    }

    public Sujet(String titre, String matiere, String description, Prof prof, List<Travail> travaux) {
        this.titre = titre;
        this.matiere = matiere;
        this.description = description;
        this.prof = prof;
        this.travaux = travaux;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    @JsonIgnore
    public List<Travail> getTravaux() {
        return travaux;
    }

    public void setTravaux(List<Travail> travaux) {
        this.travaux = travaux;
    }

    public List<PlagiatDetection> getPlagiatDetectionList() {
        return plagiatDetectionList;
    }

    public void setPlagiatDetectionList(List<PlagiatDetection> plagiatDetectionList) {
        this.plagiatDetectionList = plagiatDetectionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sujet sujet)) return false;
        return Objects.equals(getId(), sujet.getId()) && Objects.equals(getTitre(), sujet.getTitre()) && Objects.equals(getMatiere(), sujet.getMatiere()) && Objects.equals(getDescription(), sujet.getDescription()) && Objects.equals(getProf(), sujet.getProf()) && Objects.equals(getTravaux(), sujet.getTravaux());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitre(), getMatiere(), getDescription(), getProf(), getTravaux());
    }

    @Override
    public String toString() {
        return "Sujet{" +
                "id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", matiere='" + matiere + '\'' +
                ", description='" + description + '\'' +
                ", prof=" + prof +
                ", travaux=" + travaux +
                '}';
    }
}
