package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.Maquette;

public interface MaquetteRepository extends JpaRepository<Maquette,Long> {
    public Page<Maquette> findByCodeContainsIgnoreCase(String kw, Pageable pageable);
}
