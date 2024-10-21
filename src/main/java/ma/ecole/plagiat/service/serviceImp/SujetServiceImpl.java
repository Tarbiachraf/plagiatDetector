package ma.ecole.plagiat.service.serviceImp;

import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.entities.Sujet;
import ma.ecole.plagiat.entities.Travail;
import ma.ecole.plagiat.exceptions.ProfNotFoundException;
import ma.ecole.plagiat.exceptions.SujetNotFoundException;
import ma.ecole.plagiat.mappers.SujetMapper;
import ma.ecole.plagiat.reponses.SujetResponse;
import ma.ecole.plagiat.repository.ProfRepository;
import ma.ecole.plagiat.repository.SujetRepository;
import ma.ecole.plagiat.repository.TravailRepository;
import ma.ecole.plagiat.service.ProfService;
import ma.ecole.plagiat.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SujetServiceImpl implements SujetService {

    @Autowired
    private SujetRepository sujetRepository;

    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private SujetMapper sujetMapper;

    @Autowired
    private ProfService profService;
    @Autowired
    private ProfRepository profRepository;

    @Override
    public SujetResponse getSujetById(String id) {
        Sujet sujet = sujetRepository.findById(id).orElseThrow(() -> new SujetNotFoundException(id));
        return sujetMapper.toResponse(sujet);
    }

    @Override
    public List<SujetResponse> getAllSujets() {
        List<Sujet> sujets = sujetRepository.findAll();

        // Pour chaque sujet, récupérer les travaux associés
        return sujets.stream()
                .map(sujet -> {
                    return sujetMapper.toResponse(sujet);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SujetResponse createSujet(SujetDTO sujetDTO){
        Sujet sujet = sujetMapper.toEntity(sujetDTO);
        Sujet savedSujet = sujetRepository.save(sujet);
        Prof prof = profRepository.findAll().stream().findFirst().orElseThrow(()->new ProfNotFoundException());
        if(prof.getSujets()==null){
            prof.setSujets(new ArrayList<>());
        }
        prof.getSujets().add(savedSujet);
        profRepository.save(prof);
        return sujetMapper.toResponse(savedSujet);
    }

    @Override
    public SujetResponse updateSujet(String id, SujetDTO sujetDTO) {
        Sujet sujet = sujetRepository.findById(id).orElseThrow(() -> new SujetNotFoundException(id));

        sujet.setDescription(sujetDTO.description());
        sujet.setTitre(sujetDTO.titre());
        sujet.setMatiere(sujetDTO.matiere());
        Sujet updatedSujet = sujetRepository.save(sujet);

        return sujetMapper.toResponse(updatedSujet);
    }

    @Override
    public void deleteSujetById(String id) {
        Sujet sujet = sujetRepository.findById(id).orElseThrow(() -> new SujetNotFoundException(id));
        sujetRepository.delete(sujet);
    }
}