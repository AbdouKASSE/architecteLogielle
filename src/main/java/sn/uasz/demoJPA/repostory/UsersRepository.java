package sn.uasz.demoJPA.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Userss;

import java.util.List;

public interface UsersRepository extends JpaRepository<Userss,Long> {
    public List<Enseignant> findByPrenomContainsIgnoreCase(String kw);
    public Page<Userss> findById(Long username, Pageable pageable);
}
