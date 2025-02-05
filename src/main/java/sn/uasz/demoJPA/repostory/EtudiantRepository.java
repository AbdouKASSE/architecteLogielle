package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    public Page<Etudiant> findByPrenomContainsIgnoreCase(String kw, Pageable pageable);

}
