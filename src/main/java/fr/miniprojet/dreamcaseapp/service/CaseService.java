package fr.miniprojet.dreamcaseapp.service;

import org.springframework.stereotype.Service;

import fr.miniprojet.dreamcaseapp.dto.CaseDto;
import fr.miniprojet.dreamcaseapp.dto.CasePresentationDto;
import fr.miniprojet.dreamcaseapp.exception.NotFoundException;

@Service
public interface CaseService {

	 	CasePresentationDto getCaseById(Long caseId) throws NotFoundException;

	 	CasePresentationDto createCase(CaseDto caseDto);

	 	CasePresentationDto updateCase(Long caseId, CaseDto caseDto) throws NotFoundException;

	    void deleteCase(Long caseId);
}
