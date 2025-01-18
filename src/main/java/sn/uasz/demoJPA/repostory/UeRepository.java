package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.UE;

public interface UeRepository extends JpaRepository<UE,Long> {
}
