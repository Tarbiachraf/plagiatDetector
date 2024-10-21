package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Student;
import ma.ecole.plagiat.entities.Travail;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class PlagiarismDetectedEvent extends ApplicationEvent {

    private Travail travail;
    private List<Student> studentList;

    public PlagiarismDetectedEvent(Object source, Travail travail, List<Student> studentList) {
        super(source);
        this.travail = travail;
        this.studentList = studentList;
    }

    public Travail getTravail() {
        return travail;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
