package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @NonNull
    @Size(min = 3,max = 15)
    private String nom;
    private int nbEtudiant;
    @ManyToOne
    @JoinColumn(name = "classe")
    private Classe classe;
}
