package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Classe;
import sn.uasz.demoJPA.entiries.Seance;

public interface SeanceRepository extends JpaRepository<Seance,Long> {
    public Page<Seance> findAllByCodeContainsIgnoreCase(String kw, Pageable pageable);
   public Page<Seance> findBySeanceETP_id(Long kw, Pageable pageable);
}
