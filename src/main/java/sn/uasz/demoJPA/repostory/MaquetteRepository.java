package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Maquette;

public interface MaquetteRepository extends JpaRepository<Maquette,Long> {
}
