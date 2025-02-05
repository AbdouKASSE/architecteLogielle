package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Salle  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3,max = 8)
    @Column(unique = true)
    private String nom;
    private int capacite;
    private String batiment;
    @OneToMany(mappedBy = "seanceSA",fetch = FetchType.EAGER)
    private List<Seance> seance =new ArrayList<>();

}
