package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Classe;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    public Page<Classe> findByNomContainsIgnoreCase(String kw, Pageable pageable);
    public Page<Classe> findByClasseFr_Id(Long kw, Pageable pageable);
}
