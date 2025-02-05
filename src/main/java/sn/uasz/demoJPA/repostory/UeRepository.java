package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.UE;

public interface UeRepository extends JpaRepository<UE,Long> {
    public Page<UE> findByLibelleContainsIgnoreCase(String kw, Pageable pageable);
    public Page<UE> findByUeMaquette_id(Long kw, Pageable pageable);

}
