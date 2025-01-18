package sn.uasz.demoJPA.entiries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    private String heureDeb;
    private String heureFin;
}
