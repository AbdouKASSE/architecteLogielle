package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Choix;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface ChoixRepository extends JpaRepository<Choix,Long> {
    public Page<Choix> findByDateContainsIgnoreCase(String kw, Pageable pageable);
    public Page<Choix> findByEnseignants_id(long kw, Pageable pageable);
}
