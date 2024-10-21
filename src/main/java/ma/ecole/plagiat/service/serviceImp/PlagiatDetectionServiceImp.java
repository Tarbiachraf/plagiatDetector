package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.entities.PlagiatDetection;
import ma.ecole.plagiat.exceptions.PlagiatNotFoundException;
import ma.ecole.plagiat.repository.PlagiatDetectionRepository;
import ma.ecole.plagiat.service.PlagiatDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlagiatDetectionServiceImp implements PlagiatDetectionService {

    @Autowired
    private PlagiatDetectionRepository plagiatRepository;

    @Override
    public PlagiatDetection createPlagiat(PlagiatDetection plagiat) {
        return plagiatRepository.save(plagiat);
    }

    @Override
    public PlagiatDetection getPlagiatById(String id) {
        return plagiatRepository.findById(id)
                .orElseThrow(() -> new PlagiatNotFoundException("Plagiat non trouvé avec ID : " + id));
    }

    @Override
    public PlagiatDetection updatePlagiat(String id, PlagiatDetection updatedPlagiat) {
        PlagiatDetection existingPlagiat = plagiatRepository.findById(id)
                .orElseThrow(() -> new PlagiatNotFoundException("Plagiat non trouvé avec ID : " + id));

        existingPlagiat.setStudent(updatedPlagiat.getStudent());
        existingPlagiat.setSujet(updatedPlagiat.getSujet());
        existingPlagiat.setPlagiarizesWith(updatedPlagiat.getPlagiarizesWith());

        return plagiatRepository.save(existingPlagiat);
    }

    @Override
    public void deletePlaiat(String id) {
        if (!plagiatRepository.existsById(id)) {
            throw new PlagiatNotFoundException(id);
        }
        plagiatRepository.deleteById(id);
    }

    @Override
    public List<PlagiatDetection> getAllPlagiats() {
        return plagiatRepository.findAll();
    }
}
