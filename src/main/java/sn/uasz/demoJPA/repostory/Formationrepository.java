package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.Formation;

public interface Formationrepository extends JpaRepository<Formation,Long> {
    public Page<Formation> findByNomContainsIgnoreCase(String kw, Pageable pageable);
}
