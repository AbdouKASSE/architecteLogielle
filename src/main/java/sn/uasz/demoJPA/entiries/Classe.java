package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3,max = 15)
    private String nom;
    @NonNull
    private String niveau;
    private  int effectif;
    @OneToMany(mappedBy = "classe")
    List<Groupe> groupes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "classeFr")
    private Formation classeFr;
    @OneToOne(mappedBy = "classeM")
    private Maquette maquette;
}
