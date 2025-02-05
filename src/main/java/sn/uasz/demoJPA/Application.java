package sn.uasz.demoJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import sn.uasz.demoJPA.entiries.*;
import sn.uasz.demoJPA.repostory.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private EnseignantRepostory enseignantRepository;
    @Autowired
    private EnseignementRepository enseignementRepository;
    @Autowired
    private UeRepository ueRepository;
    @Autowired
    private EcRepository ecRepository;
    @Autowired
    private ChoixRepository choixRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        List<EC> list = new ArrayList<>();
        Enseignant enseig = new Enseignant();
        enseig.setNom("Diop");
        enseig.setPrenom("Ibrahima");
        enseig.setMatricule("181185/D");
        enseig.setGrade("Professeur Assimulé");

        Enseignant enseig2 = new Enseignant();
        enseig2.setNom("Fall");
        enseig2.setPrenom("Modou");
        enseig2.setMatricule("181186/A");
        enseig2.setGrade("Vacataire");

        Enseignant enseig3 = new Enseignant();
        enseig3.setNom("Ndiaye");
        enseig3.setPrenom("Ibrahima");
        enseig3.setMatricule("181187/B");
        enseig3.setGrade("Vacataire");
        Enseignant enseig4 = new Enseignant();
        enseig4.setNom("Diop");
        enseig4.setPrenom("Ibrahima");
        enseig4.setMatricule("181185/D");
        enseig4.setGrade("Professeur Assimulé");

        Enseignant enseig5 = new Enseignant();
        enseig5.setNom("Fall");
        enseig5.setPrenom("Modou");
        enseig5.setMatricule("181186/A");
        enseig5.setGrade("Vacataire");

        Enseignant enseig6 = new Enseignant(); 
        enseig6.setNom("Ndiaye");
        enseig6.setPrenom("Ibrahima");
        enseig6.setMatricule("181187/B");
        enseig6.setGrade("Vacataire");
        Enseignant enseig7 = new Enseignant();
        enseig7.setNom("Diop");
        enseig7.setPrenom("Ibrahima");
        enseig7.setMatricule("181185/D");
        enseig7.setGrade("Professeur Assimulé");

        Enseignant enseig8 = new Enseignant();
        enseig8.setNom("Fall");
        enseig8.setPrenom("Modou");
        enseig8.setMatricule("181186/A");
        enseig8.setGrade("Vacataire");

        Enseignant enseig9 = new Enseignant();
        enseig9.setNom("Ndiaye");
        enseig9.setPrenom("Ibrahima");
        enseig9.setMatricule("181187/B");
        enseig9.setGrade("Vacataire");
        Enseignant enseig10 = new Enseignant();
        enseig10.setNom("Diop");
        enseig10.setPrenom("Ibrahima");
        enseig10.setMatricule("181185/D");
        enseig10.setGrade("Professeur Assimulé");

        Enseignant enseig11 = new Enseignant();
        enseig11.setNom("Fall");
        enseig11.setPrenom("Modou");
        enseig11.setMatricule("181186/A");
        enseig11.setGrade("Vacataire");

        Enseignant enseig12 = new Enseignant();
        enseig12.setNom("Ndiaye");
        enseig12.setPrenom("Ibrahima");
        enseig12.setMatricule("181187/B");
        enseig12.setGrade("Vacataire");
        Enseignant enseig13 = new Enseignant();
        enseig13.setNom("Diop");
        enseig13.setPrenom("Ibrahima");
        enseig13.setMatricule("181185/D");
        enseig13.setGrade("Professeur Assimulé");

        Enseignant enseig14 = new Enseignant();
        enseig14.setNom("Fall");
        enseig14.setPrenom("Modou");
        enseig14.setMatricule("181186/A");
        enseig14.setGrade("Vacataire");

        Enseignant enseig15 = new Enseignant();
        enseig15.setNom("Ndiaye");
        enseig15.setPrenom("Ibrahima");
        enseig15.setMatricule("181187/B");
        enseig15.setGrade("Vacataire");
        Enseignant enseig16 = new Enseignant();
        enseig16.setNom("Diop");
        enseig16.setPrenom("Ibrahima");
        enseig16.setMatricule("181185/D");
        enseig16.setGrade("Professeur Assimulé");

        Enseignant enseig17 = new Enseignant();
        enseig17.setNom("Fall");
        enseig17.setPrenom("Modou");
        enseig17.setMatricule("181186/A");
        enseig17.setGrade("Vacataire");

        Enseignant enseig18 = new Enseignant();
        enseig18.setNom("Ndiaye");
        enseig18.setPrenom("Ibrahima");
        enseig18.setMatricule("181187/B");
        enseig18.setGrade("Vacataire");

        Etudiant etud = new Etudiant();
        etud.setNom("KASSE");
        etud.setPrenom("Abdou");
        etud.setMatricule("181186/A");
        etud.setNiveau("M2");

        Etudiant etud2 = new Etudiant();
        etud2.setNom("SENE");
        etud2.setPrenom("Baboucar");
        etud2.setMatricule("181186/B");
        etud2.setNiveau("M2");


        UE ue = new UE(null,"INF111","Arch",3,2,10,10,10,null,null);
        EC ec = new EC(null,"INF1111","Arch",3,2,10,10,10,ue,null);
        list.add(ec);
        Enseignement enseign = new Enseignement(null, "INF11", "ARCT", "CM", ec, null,null);

       /* usersRepository.save(enseig);
        usersRepository.save(enseig2);
        usersRepository.save(enseig3);
        usersRepository.save(enseig4);
        usersRepository.save(enseig5);
        usersRepository.save(enseig6);
        usersRepository.save(enseig7);
        usersRepository.save(enseig8);
        usersRepository.save(enseig9);
        usersRepository.save(enseig10);
        usersRepository.save(enseig11);
        usersRepository.save(enseig12);
        usersRepository.save(enseig13);
        usersRepository.save(enseig14);
        usersRepository.save(enseig15);
        usersRepository.save(enseig16);
        usersRepository.save(enseig17);
        usersRepository.save(enseig18);

        usersRepository.save(etud);
        usersRepository.save(etud2);

        ueRepository.save(ue);
        ecRepository.save(ec);
        enseignementRepository.save(enseign);
        Enseignant en3 = enseignantRepository.findById(1L).get();
        Enseignement enseignement = enseignementRepository.findById(1L).get();
        EC ec3 = ecRepository.findById(1L).get();

        choixRepository.save(new Choix(null,"12/01/2025",en3,enseignement,0));
*/
        List<Enseignant> enseignants = enseignantRepository.findAll();

        enseignants.forEach(enseignant -> {

            System.out.println("-----------------------------------");
            System.out.println(enseignant.getId());
            System.out.println(enseignant.getPrenom());
            System.out.println(enseignant.getNom());
            System.out.println(enseignant.getMatricule());
            System.out.println(enseignant.getGrade());
        });

       List<Etudiant> etudiants = etudiantRepository.findAll();

        etudiants.forEach(enseignant -> {

            System.out.println("-----------------------------------");
            System.out.println(enseignant.getId());
            System.out.println(enseignant.getPrenom());
            System.out.println(enseignant.getNom());
            System.out.println(enseignant.getMatricule());
            System.out.println(enseignant.getNiveau());
        });
        List<Enseignement> enseignements = enseignementRepository.findAll();

        enseignements.forEach(enseignant1 -> {

            System.out.println("--------------------");
            System.out.println(enseignant1.getCode());
            System.out.println(enseignant1.getLibelle());
            System.out.println(enseignant1.getType());
        });
        List<UE> ues = ueRepository.findAll();

        ues.forEach(enseignant1 -> {

            System.out.println("--------------------");
            System.out.println(enseignant1.getCode());
            System.out.println(enseignant1.getLibelle());
            System.out.println(enseignant1.getCredit());
            System.out.println(enseignant1.getCm());
            System.out.println(enseignant1.getCoef());
            System.out.println(enseignant1.getId());
            System.out.println(enseignant1.getTp());
            //System.out.println(enseignant1.getEcs());
        });
        List<EC> ecs = ecRepository.findAll();

        ecs.forEach(enseignant1 -> {

            System.out.println("--------------------");
            System.out.println(enseignant1.getCode());
            System.out.println(enseignant1.getLibelle());
            System.out.println(enseignant1.getCredit());
            System.out.println(enseignant1.getCm());
            System.out.println(enseignant1.getCoef());
            System.out.println(enseignant1.getId());
            System.out.println(enseignant1.getTp());
        });

        List<Choix> cho = choixRepository.findAll();
        cho.forEach(enseignant1 -> {

            System.out.println("--------------------Choix");
            System.out.println(enseignant1.getEnseignement_id().getCode());
            System.out.println(enseignant1.getEnseignement_id().getType());
            System.out.println(enseignant1.getEnseignants().getNom());
            System.out.println(enseignant1.getEnseignants().getPrenom());
            System.out.println(enseignant1.getDate());


        });

    }
    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager userDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails u1 = userDetailsManager.loadUserByUsername("FALL");
            if(u1==null)
                userDetailsManager.createUser(User.withUsername("FALL").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            UserDetails u2 = userDetailsManager.loadUserByUsername("KASSE");
            if (u2==null)
                userDetailsManager.createUser(User.withUsername("KASSE").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
                );
        };
    }

}
