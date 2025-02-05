package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @NonNull
    @Size(min = 4,max = 15)

    private String code;
    @NonNull
    private String libelle;
    private int credit;
    private int coef;
    private int cm;
    private int td;
    private int tp;
    @ManyToOne
    @JoinColumn(name = "ueEC")
    private UE ueEC;
    @OneToMany(mappedBy = "ecEnseignement")
    private List<Enseignement> enseignement;

}
