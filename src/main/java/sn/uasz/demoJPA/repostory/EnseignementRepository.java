package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignement;

public interface EnseignementRepository extends JpaRepository<Enseignement,Long> {
}
