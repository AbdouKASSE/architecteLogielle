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
public class Enseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NonNull
    @Size(min = 4,max = 15)
    @Column(unique = true)
    private  String code;
    private String libelle;
    private  String type;

    @ManyToOne
    @JoinColumn(name = "ecEnseignement")
    private EC ecEnseignement;

    @OneToMany(mappedBy = "enseignement_id",fetch =  FetchType.EAGER)
    private List<Choix> choix = new ArrayList<>();
    @OneToMany(mappedBy = "seanceENG")
    private List<Seance> seance = new ArrayList<>();

}
