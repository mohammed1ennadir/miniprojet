package fr.miniprojet.dreamcaseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.miniprojet.dreamcaseapp.entity.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long>{

}
