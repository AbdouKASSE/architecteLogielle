package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String code;
    @NotEmpty
    private String heureDeb;
    @NotEmpty
    private String heureFin;
    @ManyToOne // Relation OneToOne
    @JoinColumn(name = "seanceENG")
    private Enseignement seanceENG;
    @ManyToOne // Relation OneToOne
    @JoinColumn(name = "seanceSA", referencedColumnName = "id")
    private Salle seanceSA;
    @ManyToOne
    @JoinColumn(name = "seanceETP")
    private EmploisDuTp seanceETP;
    @OneToMany(mappedBy = "deroulementSC")
    private List<Deroulement> deroulement = new ArrayList<>();

}
