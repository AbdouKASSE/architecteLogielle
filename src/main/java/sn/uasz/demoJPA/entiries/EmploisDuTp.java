package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmploisDuTp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 4,max = 8)
    private String semestre;
    private String annee;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    @OneToMany(mappedBy = "seanceETP",fetch =  FetchType.EAGER)
    private List<Seance> seances = new ArrayList<>();

}
