package ma.ecole.plagiat.events;

import ma.ecole.plagiat.entities.Travail;
import org.springframework.context.ApplicationEvent;


public class TravailSoumisEvent extends ApplicationEvent {
    private Travail travail;
    public TravailSoumisEvent(Object source, Travail travail) {
        super(source);
        this.travail = travail;
    }

    public Travail getTravail() {
        return travail;
    }
}
