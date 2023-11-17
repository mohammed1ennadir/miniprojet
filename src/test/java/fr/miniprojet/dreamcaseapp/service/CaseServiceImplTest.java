package fr.miniprojet.dreamcaseapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.miniprojet.dreamcaseapp.dto.CaseDto;
import fr.miniprojet.dreamcaseapp.dto.CasePresentationDto;
import fr.miniprojet.dreamcaseapp.entity.Case;
import fr.miniprojet.dreamcaseapp.exception.NotFoundException;
import fr.miniprojet.dreamcaseapp.mapper.CaseMapper;
import fr.miniprojet.dreamcaseapp.repository.CaseRepository;
import fr.miniprojet.dreamcaseapp.service.impl.CaseServiceImpl;

@ExtendWith(MockitoExtension.class)
class CaseServiceImplTest {

	@Mock
    private CaseRepository caseRepository;

    @Mock
    private CaseMapper caseMapper;

    @InjectMocks
    private CaseServiceImpl caseService;
 	@Test
    void testGetCaseByIdNotFound() {
        // Given
        Long caseId = 1L;

        when(caseRepository.findById(caseId)).thenReturn(Optional.empty());

        // When and Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> caseService.getCaseById(caseId));
        assertEquals("Case not found with id: 1", exception.getMessage());

    }
 	
 	@Test
    void testGetCaseByIdFound() throws NotFoundException {
        // Given
        Long caseId = 1L;
        Case caseEntity = new Case();
        caseEntity.setCaseId(caseId);
        when(caseRepository.findById(caseId)).thenReturn(Optional.of(caseEntity));
        when(caseMapper.toPresentationDto(caseEntity)).thenReturn(new CasePresentationDto(caseId,LocalDateTime.now(),LocalDateTime.now(), "Test Title", "Test Description"));

        // When
        CasePresentationDto result = caseService.getCaseById(caseId);

        // Then
        assertEquals(caseId, result.getCaseId());
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        verify(caseRepository, times(1)).findById(caseId);
        verify(caseMapper, times(1)).toPresentationDto(caseEntity);
    }
 	
 	 @Test
     void testCreateCase() {
         Long caseId = 1L;

         // Given
         CaseDto caseRequestDto = new CaseDto("Test Title", "Test Description");
         Case caseEntity = new Case();
         when(caseMapper.toCase(caseRequestDto)).thenReturn(caseEntity);
         when(caseRepository.save(caseEntity)).thenReturn(caseEntity);
         //when(caseMapper.tod(caseEntity)).thenReturn(new CaseDto("Test Title", "Test Description"));
         when(caseMapper.toPresentationDto(caseEntity)).thenReturn(new CasePresentationDto(caseId,LocalDateTime.now(),LocalDateTime.now(), "Test Title", "Test Description"));

         // When
         CasePresentationDto result = caseService.createCase(caseRequestDto);

         // Then
         assertEquals(1L, result.getCaseId());
         assertEquals("Test Title", result.getTitle());
         assertEquals("Test Description", result.getDescription());
         verify(caseRepository, times(1)).save(caseEntity);
         verify(caseMapper, times(1)).toCase(caseRequestDto);
         verify(caseMapper, times(1)).toPresentationDto(caseEntity);
     }
 	 @Test
     void testUpdateCaseByIdNotFound() {
 		  // Given
         Long caseId = 1L;
         CaseDto caseRequestDto = new CaseDto("Test Title", "Test Description");

         when(caseRepository.findById(caseId)).thenReturn(Optional.empty());

         // When and Then
         NotFoundException exception = assertThrows(NotFoundException.class, () -> caseService.updateCase(caseId,caseRequestDto));
         assertEquals("Case not found with id: 1", exception.getMessage());

 	 }
 	 @Test
     void testUpdateCase() throws NotFoundException {
         // Given
         Long caseId = 1L;
         CaseDto caseRequestDto = new CaseDto("Test Title", "Test Description");
         Case existingCase = new Case();
         existingCase.setCaseId(caseId);
         when(caseRepository.findById(caseId)).thenReturn(Optional.of(existingCase));
         when(caseRepository.save(existingCase)).thenReturn(existingCase);
         when(caseMapper.toPresentationDto(existingCase)).thenReturn(new CasePresentationDto(caseId,LocalDateTime.now(),LocalDateTime.now(), "Updated Title", "Updated Description"));

         // When
         CasePresentationDto result = caseService.updateCase(caseId, caseRequestDto);

         // Then
         assertEquals(caseId, result.getCaseId());
         assertEquals("Updated Title", result.getTitle());
         assertEquals("Updated Description", result.getDescription());
         verify(caseRepository, times(1)).findById(caseId);
         verify(caseRepository, times(1)).save(existingCase);
         verify(caseMapper, times(1)).toPresentationDto(existingCase);
     }
 	 
    @Test
    void testDeleteCase() {
        Long caseId = 1L;

        caseService.deleteCase(caseId);

        verify(caseRepository, times(1)).deleteById(caseId);
    }
   
}
