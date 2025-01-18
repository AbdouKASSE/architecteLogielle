package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Salle;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
