package ma.ecole.plagiat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "profs")
@AllArgsConstructor
@NoArgsConstructor
public class Prof implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String department;

    public Prof(String name, String email, String department, List<Sujet> sujets, List<Student> students) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.sujets = sujets;
        this.students = students;
    }

    @DBRef
    @JsonIgnore
    private List<Sujet> sujets;  // Les sujets publiés par le professeur

    @DBRef
    @JsonIgnore
    private List<Student> students;

    @CreatedDate
    private LocalDateTime createdAt;



    @LastModifiedDate
    private LocalDateTime updatedAt;

    @JsonIgnore
    public List<Sujet> getSujets() {
        return sujets;
    }

    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Prof{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prof prof)) return false;
        return Objects.equals(getId(), prof.getId()) && Objects.equals(getName(), prof.getName()) && Objects.equals(getEmail(), prof.getEmail()) && Objects.equals(getDepartment(), prof.getDepartment()) && Objects.equals(getSujets(), prof.getSujets()) && Objects.equals(getStudents(), prof.getStudents()) && Objects.equals(getCreatedAt(), prof.getCreatedAt()) && Objects.equals(getUpdatedAt(), prof.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getDepartment(), getSujets(), getStudents(), getCreatedAt(), getUpdatedAt());
    }
}
