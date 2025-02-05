package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface EnseignementRepository extends JpaRepository<Enseignement,Long> {
    public Page<Enseignement> findByCodeContainsIgnoreCase(String kw, Pageable pageable);
    public Page<Enseignement> findByEcEnseignement_id(Long kw, Pageable pageable);
}
