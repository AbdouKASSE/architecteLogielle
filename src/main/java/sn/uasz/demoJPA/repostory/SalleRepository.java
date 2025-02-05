package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.Salle;

public interface SalleRepository extends JpaRepository<Salle,Long> {
    public Page<Salle> findByNomContainsIgnoreCase(String kw, Pageable pageable);
}
