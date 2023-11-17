package fr.miniprojet.dreamcaseapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.miniprojet.dreamcaseapp.dto.CaseDto;
import fr.miniprojet.dreamcaseapp.dto.CasePresentationDto;
import fr.miniprojet.dreamcaseapp.exception.NotFoundException;
import fr.miniprojet.dreamcaseapp.service.CaseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
public class CaseController {
	public final CaseService caseService;
	
	@GetMapping("/{caseId}")
	public ResponseEntity<CasePresentationDto> getCaseById(@PathVariable Long caseId) throws NotFoundException {
	    return ResponseEntity.ok(caseService.getCaseById(caseId));
	}
	
	@PostMapping
    public ResponseEntity<CasePresentationDto> createCase(@RequestBody CaseDto caseDto) {
        return new ResponseEntity<>(caseService.createCase(caseDto), HttpStatus.CREATED);
    }
	
    @PutMapping("/{caseId}")
    public ResponseEntity<CasePresentationDto> updateCase(@PathVariable Long caseId, @RequestBody CaseDto caseDto)throws NotFoundException {
        return ResponseEntity.ok(caseService.updateCase(caseId, caseDto));
    }
    
    @DeleteMapping("/{caseId}")
    public ResponseEntity<Void> deleteCase(@PathVariable Long caseId) {
        caseService.deleteCase(caseId);
        return ResponseEntity.noContent().build();
    }
	
	
}
