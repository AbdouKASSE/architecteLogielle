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
public class Enseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String code;
    private String libelle;
    private  String type;

    @ManyToOne
    @JoinColumn(name = "ec_id")
    private EC ec_id;

    @OneToMany(mappedBy = "enseignement_id",fetch =  FetchType.EAGER)
    private List<Choix> choix = new ArrayList<>();

}
