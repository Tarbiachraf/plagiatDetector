package ma.ecole.plagiat.service;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.reponses.ProfResponse;

public interface ProfService {
    //ProfResponse createProf(ProfDTO profDTO);
    ProfResponse getOurProf();

//    Prof getProf();

 //   List<ProfResponse> getAllProfs();
 //   ProfResponse updateProf(String id, ProfDTO profDetails);
    void deleteProf();

    ProfResponse createOrUpdateProf(ProfDTO profDTO);
}
