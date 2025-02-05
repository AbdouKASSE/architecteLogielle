package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
@DiscriminatorValue("PROF")
public class Enseignant extends Userss {
    private String grade;
    @OneToMany(mappedBy = "enseignants",fetch =  FetchType.EAGER)
    private List<Choix> choix = new ArrayList<>();


}
