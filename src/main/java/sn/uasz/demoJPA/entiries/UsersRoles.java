package sn.uasz.demoJPA.entiries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roles;
}
