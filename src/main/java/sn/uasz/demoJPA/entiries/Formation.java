package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NonNull
    @Column(unique = true)
    private String code;
    private String nom;
    @OneToMany(mappedBy = "classeFr")
    List<Classe> classes = new ArrayList<>();
}
