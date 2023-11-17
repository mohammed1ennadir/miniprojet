package fr.miniprojet.dreamcaseapp.mapper;

import org.mapstruct.Mapper;
import fr.miniprojet.dreamcaseapp.entity.Case;
import fr.miniprojet.dreamcaseapp.dto.CaseDto;
import fr.miniprojet.dreamcaseapp.dto.CasePresentationDto;

@Mapper(componentModel = "spring")
public interface CaseMapper {
	
	CasePresentationDto toPresentationDto(Case caseE);

	Case toCase(CaseDto caseDto);
}
