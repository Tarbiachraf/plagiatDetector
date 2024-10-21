package ma.ecole.plagiat.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "plagiats")
public class PlagiatDetection implements Serializable {
    @Id
    private String id;

    @DBRef
    private Student student;

    @DBRef
    private Sujet sujet;
    @DBRef
    private List<Student> plagiarizesWith;

    public PlagiatDetection() {
    }

    public PlagiatDetection(Student student, Sujet sujet, List<Student> plagiarizesWith) {
        this.student = student;
        this.sujet = sujet;
        this.plagiarizesWith = plagiarizesWith;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public List<Student> getPlagiarizesWith() {
        return plagiarizesWith;
    }

    public void setPlagiarizesWith(List<Student> plagiarizesWith) {
        this.plagiarizesWith = plagiarizesWith;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlagiatDetection that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStudent(), that.getStudent()) && Objects.equals(getSujet(), that.getSujet()) && Objects.equals(getPlagiarizesWith(), that.getPlagiarizesWith());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudent(), getSujet(), getPlagiarizesWith());
    }

    @Override
    public String toString() {
        return "PlagiatDetection{" +
                "id='" + id + '\'' +
                ", student=" + student +
                ", sujet=" + sujet +
                ", plagiarizesWith=" + plagiarizesWith +
                '}';
    }
}
