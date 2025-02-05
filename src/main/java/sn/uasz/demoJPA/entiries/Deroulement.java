package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Deroulement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String heureDeb;
    @NonNull
    private String heureFin;
    @ManyToOne// Relation OneToOne
    @JoinColumn(name = "deroulementSC")
    private Seance deroulementSC;
    private String description;
    private LocalDate datee;
}
