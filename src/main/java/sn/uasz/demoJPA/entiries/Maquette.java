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
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @OneToMany(mappedBy = "ues")
    List<UE> ues;
    @OneToOne(cascade = CascadeType.ALL) // Relation OneToOne
    @JoinColumn(name = "classe", referencedColumnName = "id")
    private Classe classe;

}
