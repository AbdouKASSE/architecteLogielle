package sn.uasz.demoJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        Enseignement enseign = new Enseignement(null, "INF11", "ARCT", "CM", ec, null);

        usersRepository.save(enseig);
        usersRepository.save(enseig2);
        usersRepository.save(enseig3);

        usersRepository.save(etud);
        usersRepository.save(etud2);

        ueRepository.save(ue);
        ecRepository.save(ec);
        enseignementRepository.save(enseign);
        Enseignant en3 = enseignantRepository.findById(1L).get();
        Enseignement enseignement = enseignementRepository.findById(1L).get();
        EC ec3 = ecRepository.findById(1L).get();

        choixRepository.save(new Choix(null,"12/01/2025",en3,enseignement));


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
            System.out.println(enseignant1.getEnseignant_id().getNom());
            System.out.println(enseignant1.getEnseignant_id().getPrenom());
            System.out.println(enseignant1.getDate());


        });

    }

}
