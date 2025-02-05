package sn.uasz.demoJPA.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.demoJPA.entiries.UsersRoles;

public interface UsersRoleRepository extends JpaRepository<UsersRoles,Long> {
}
