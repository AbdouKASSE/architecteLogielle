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
public class UE {
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
    @OneToMany(mappedBy = "ue_id",fetch =  FetchType.EAGER)
    private List<EC> ecs = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "ues")
    private Maquette ues;

}
