package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Deroulement;

public interface DeroulementRepository extends JpaRepository<Deroulement,Long> {
}
