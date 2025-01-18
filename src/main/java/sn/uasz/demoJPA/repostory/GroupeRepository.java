package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
}
