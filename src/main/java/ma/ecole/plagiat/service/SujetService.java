package ma.ecole.plagiat.service;

import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.reponses.SujetResponse;

import java.util.List;

public interface SujetService {
    SujetResponse createSujet(SujetDTO sujet);
    SujetResponse getSujetById(String id);
    List<SujetResponse> getAllSujets();
    SujetResponse updateSujet(String id, SujetDTO sujetDetails);
    void deleteSujetById(String id);
}
