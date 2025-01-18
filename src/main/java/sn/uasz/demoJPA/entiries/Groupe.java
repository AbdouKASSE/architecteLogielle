package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private int nbEtudiant;
    @ManyToOne
    @JoinColumn(name = "classe")
    private Classe classe;
}
