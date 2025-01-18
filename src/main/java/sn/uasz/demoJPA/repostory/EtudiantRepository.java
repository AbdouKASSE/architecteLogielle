package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
