package ma.ecole.plagiat.controller;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.reponses.ProfResponse;
import ma.ecole.plagiat.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profs")
public class ProfController {

    @Autowired
    private ProfService profService;

    @GetMapping
    public ResponseEntity<ProfResponse> getProf() {
        ProfResponse profResponse = profService.getOurProf();
        return ResponseEntity.ok(profResponse);  // Exception ProfNotFoundException gérée par le service
    }

    @PostMapping
    public ResponseEntity<ProfResponse> createOrUpdateProf(@RequestBody ProfDTO profDTO) {
        ProfResponse createdProf = profService.createOrUpdateProf(profDTO);
        return ResponseEntity.ok(createdProf);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProf() {
        profService.deleteProf();  // Exception ProfNotFoundException gérée par le service
        return ResponseEntity.noContent().build();
    }
}
