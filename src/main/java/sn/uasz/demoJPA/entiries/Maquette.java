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
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 4,max = 15)
    @Column(unique = true)
    private String code;
    private String nom;
    @OneToMany(mappedBy = "ueMaquette")
    List<UE> ues;
    @OneToOne(cascade = CascadeType.ALL) // Relation OneToOne
    @JoinColumn(name = "classeM", referencedColumnName = "id")
    private Classe classeM;

}
