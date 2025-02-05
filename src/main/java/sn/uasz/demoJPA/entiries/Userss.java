package sn.uasz.demoJPA.entiries;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type" ,length = 4)
public class Userss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @NotNull
    @Size(min = 2,max = 10)
    private String nom;
    private  String prenom;
    @NotNull
    @Size(min = 2,max = 10)
    private  String matricule;
//    @NotNull
//    @Size(min = 5,max = 10)
//    @Column(unique = true)
//    private String username;
//    private String passwd;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<UsersRoles> roles;

}
