package sn.uasz.demoJPA.entiries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

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
   private LocalDate dateDeb;
   private LocalDate dateFin;
}
