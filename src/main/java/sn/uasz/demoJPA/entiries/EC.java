package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String code;
    private String libelle;
    private int credit;
    private int coef;
    private int cm;
    private int td;
    private int tp;
    @ManyToOne
    @JoinColumn(name = "ue_id")
    private UE ue_id;

    @OneToMany(mappedBy = "ec_id")
    private List<Enseignement> enseignement;

}
