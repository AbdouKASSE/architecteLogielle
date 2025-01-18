package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public List<Enseignant> findByPrenomContainsIgnoreCase(String kw);
}
