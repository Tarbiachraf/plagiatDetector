package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.exceptions.ProfNotFoundException;
import ma.ecole.plagiat.mappers.ProfMapper;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import ma.ecole.plagiat.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProfServiceImpl implements ProfService {

    @Autowired
    private ProfRepository profRepository;

    @Autowired
    private ProfMapper profMapper;

    @Override
    @Cacheable(value = "profCache", key = "'ourProf'")
    public ProfResponse getOurProf() {

        Prof prof = profRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new ProfNotFoundException());
        ProfResponse response = profMapper.toResponse(prof);
        return response;
    }


//    @Override
//    public List<ProfResponse> getAllProfs() {
//        List<Prof> profs = profRepository.findAll();
//        return profs.stream().map(profMapper::toResponse).collect(Collectors.toList());
//    }
//
//    @Override
//    public ProfResponse createProf(ProfDTO profDTO) {
//        Prof prof = profMapper.toEntity(profDTO);
//        Prof savedProf = profRepository.save(prof);
//        return profMapper.toResponse(savedProf);
//    }
//
//    @Override
//    public ProfResponse updateProf(String id, ProfDTO profDTO) {
//        Prof prof = profRepository.findById(id).orElseThrow(() -> new ProfNotFoundException());
//        prof.setId(id);
//        prof.setEmail(profDTO.email());
//        prof.setName(profDTO.name());
//        Prof updatedProf = profRepository.save(prof);
//        return profMapper.toResponse(updatedProf);
//    }

    @Override
    @CacheEvict(value = "profCache", key = "'ourProf'")
    public void deleteProf() {
        Prof prof = profRepository.findAll().stream().findFirst().orElseThrow(() -> new ProfNotFoundException());
        profRepository.delete(prof);
    }

    @Override
    @CachePut(value = "profCache", key = "'ourProf'")
    public ProfResponse createOrUpdateProf(ProfDTO profDTO) {
        // Créer ou mettre à jour l'unique professeur
        Prof prof = profRepository.findAll().stream().findFirst().orElse(null);

        if (prof != null) {
            // Mettre à jour
            prof.setName(profDTO.name());
            prof.setEmail(profDTO.email());
            prof.setDepartment(profDTO.department());
        } else {
            // Créer un nouveau professeur
            prof = profMapper.toEntity(profDTO);
        }

        Prof savedProf = profRepository.save(prof);
        return profMapper.toResponse(savedProf);
    }
}
