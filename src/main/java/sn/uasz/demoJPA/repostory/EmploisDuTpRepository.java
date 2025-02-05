package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.EmploisDuTp;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface EmploisDuTpRepository extends JpaRepository<EmploisDuTp,Long> {
    public Page<EmploisDuTp> findBySemestreContainsIgnoreCase(String kw, Pageable pageable);


}
