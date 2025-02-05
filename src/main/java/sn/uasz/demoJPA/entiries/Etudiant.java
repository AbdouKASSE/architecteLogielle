package sn.uasz.demoJPA.entiries;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@DiscriminatorValue("ETUD")
public class Etudiant extends Userss {
    private String niveau;
}
