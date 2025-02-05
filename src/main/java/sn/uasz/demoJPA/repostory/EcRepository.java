package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.EC;
import sn.uasz.demoJPA.entiries.Enseignant;

public interface EcRepository extends JpaRepository<EC,Long> {
    public Page<EC> findByLibelleContainsIgnoreCase(String kw, Pageable pageable);
    public Page<EC> findByUeEC_id(Long kw, Pageable pageable);
}
