package fr.miniprojet.dreamcaseapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CasePresentationDto {

	private Long caseId;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdateDate;

    private String title;

    private String description;
}
