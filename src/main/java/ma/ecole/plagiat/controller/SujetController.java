package ma.ecole.plagiat.controller;


import ma.ecole.plagiat.dtos.SujetDTO;
import ma.ecole.plagiat.reponses.SujetResponse;
import ma.ecole.plagiat.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sujets")
public class SujetController {

    @Autowired
    private SujetService sujetService;

    @GetMapping("/{id}")
    public ResponseEntity<SujetResponse> getSujetById(@PathVariable String id) {
        SujetResponse sujetResponse = sujetService.getSujetById(id);
        return ResponseEntity.ok(sujetResponse);  // Exception SujetNotFoundException gérée par le service
    }

    @GetMapping
    public ResponseEntity<List<SujetResponse>> getAllSujets() {
        List<SujetResponse> sujets = sujetService.getAllSujets();
        return ResponseEntity.ok(sujets);
    }

    @PostMapping
    public ResponseEntity<SujetResponse> createSujet(@RequestBody SujetDTO sujetDTO) {
        SujetResponse createdSujet = sujetService.createSujet(sujetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSujet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SujetResponse> updateSujet(@PathVariable String id, @RequestBody SujetDTO sujetDTO) {
        SujetResponse updatedSujet = sujetService.updateSujet(id, sujetDTO);
        return ResponseEntity.ok(updatedSujet);  // Exception SujetNotFoundException gérée par le service
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSujet(@PathVariable String id) {
        sujetService.deleteSujetById(id);  // Exception SujetNotFoundException gérée par le service
        return ResponseEntity.noContent().build();
    }
}