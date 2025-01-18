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
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant_id;

    @ManyToOne
    @JoinColumn(name = "enseignement_id")
    private Enseignement enseignement_id;

}
