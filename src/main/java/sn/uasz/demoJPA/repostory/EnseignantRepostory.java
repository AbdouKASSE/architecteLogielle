package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignant;


import java.util.List;

public interface EnseignantRepostory extends JpaRepository<Enseignant,Long> {
    public Page<Enseignant> findByPrenomContainsIgnoreCase(String kw, Pageable pageable);



}

