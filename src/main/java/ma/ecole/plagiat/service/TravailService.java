package ma.ecole.plagiat.service;

import ma.ecole.plagiat.dtos.TravailDTO;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.reponses.TravailResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface TravailService {
    TravailResponse createTravail(TravailDTO travail, File pdfFile) throws IOException;
    TravailResponse getTravailById(String id);
    List<TravailResponse> getAllTravaux();
    //TravailDTO updateTravail(String id, TravailDTO travailDetails);
    void deleteTravailById(String id);
}
