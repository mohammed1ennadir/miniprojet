package fr.miniprojet.dreamcaseapp.service.impl;



import org.springframework.stereotype.Service;

import fr.miniprojet.dreamcaseapp.dto.CaseDto;
import fr.miniprojet.dreamcaseapp.dto.CasePresentationDto;
import fr.miniprojet.dreamcaseapp.entity.Case;
import fr.miniprojet.dreamcaseapp.exception.NotFoundException;
import fr.miniprojet.dreamcaseapp.mapper.CaseMapper;
import fr.miniprojet.dreamcaseapp.repository.CaseRepository;
import fr.miniprojet.dreamcaseapp.service.CaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseServiceImpl implements CaseService {

	public final CaseRepository caseRepository;
	public final CaseMapper mapper;

	@Override
    public CasePresentationDto getCaseById(Long caseId) throws NotFoundException {
        log.info("Fetching case with ID: {}", caseId);
		return mapper.toPresentationDto(caseRepository.findById(caseId)
                .orElseThrow(() -> new NotFoundException("Case not found with id: " + caseId)));
    }
	@Override
    public CasePresentationDto createCase(CaseDto newCase) {
        log.info("Creating a new case with title: {} and description: {}", newCase.getTitle(),newCase.getDescription());
        return mapper.toPresentationDto(caseRepository.save(mapper.toCase(newCase)));
    }

	@Override
	public CasePresentationDto updateCase(Long caseId, CaseDto caseDto) throws NotFoundException {
        log.info("Updating case with ID: {}", caseId);
		Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new NotFoundException("Case not found with id: " + caseId));

        existingCase.setTitle(caseDto.getTitle());
        existingCase.setDescription(caseDto.getDescription());

        return mapper.toPresentationDto(caseRepository.save(existingCase));
	}

	@Override
	public void deleteCase(Long caseId) {
		log.info("Deleting case with ID: {}", caseId);
        caseRepository.deleteById(caseId);		
	}
}
