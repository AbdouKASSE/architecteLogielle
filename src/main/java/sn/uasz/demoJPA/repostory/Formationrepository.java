package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Formation;

public interface Formationrepository extends JpaRepository<Formation,Long> {
}
