package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Choix {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String date;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "enseignants")
    private Enseignant enseignants;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "enseignement_id")
    private Enseignement enseignement_id;
    private int validation;

}
