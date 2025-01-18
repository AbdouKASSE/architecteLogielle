package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
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
    private String nom;
    @OneToMany(mappedBy = "classe")
    List<Groupe> groupes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "classe")
    private Formation classe;
    @OneToOne(mappedBy = "classe")
    private Maquette maquette;
}
