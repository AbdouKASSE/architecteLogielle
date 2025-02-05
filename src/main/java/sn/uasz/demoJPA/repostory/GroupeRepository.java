package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    public Page<Groupe> findByNomContainsIgnoreCase(String kw, Pageable pageable);
    public Page<Groupe> findByClasse_id(Long kw, Pageable pageable);
}
