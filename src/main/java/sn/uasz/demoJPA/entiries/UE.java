package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NotEmpty(message = "Le code ne doit pas être vide.")
    @Size(min = 4, max = 15, message = "Le code doit comporter entre 4 et 15 caractères.")
    @Column(unique = true)
    private String code;
    @NotEmpty(message = "Le libellé ne doit pas être vide.")
    private String libelle;

    private int credit;
    private int coef;
    private int cm;
    private int td;
    private int tp;
    @OneToMany(mappedBy = "ueEC",fetch =  FetchType.EAGER)
    private List<EC> ueEcs = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "ueMaquette")
    private Maquette ueMaquette;

}
