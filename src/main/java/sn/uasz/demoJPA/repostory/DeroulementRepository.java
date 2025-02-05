package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Deroulement;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface DeroulementRepository extends JpaRepository<Deroulement,Long> {
    public Page<Deroulement> findByHeureDebContainsIgnoreCase(String kw, Pageable pageable);
}
