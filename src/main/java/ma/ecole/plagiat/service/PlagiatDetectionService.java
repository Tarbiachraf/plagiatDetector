package ma.ecole.plagiat.service;

import ma.ecole.plagiat.entities.PlagiatDetection;

import java.util.List;

public interface PlagiatDetectionService {
    PlagiatDetection createPlagiat(PlagiatDetection plagiatDetection);

    PlagiatDetection getPlagiatById(String id);

    PlagiatDetection updatePlagiat(String Id, PlagiatDetection plagiatDetection);

    void deletePlaiat(String id);

    List<PlagiatDetection> getAllPlagiats();
}
