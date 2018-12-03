package monRDV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import monRDV.model.Adresse;
import monRDV.model.CreneauDisponible;
import monRDV.model.Lieu;
import monRDV.model.Modalite;
import monRDV.model.Motif;
import monRDV.model.Patient;
import monRDV.model.Praticien;
import monRDV.model.Profil;
import monRDV.model.RendezVous;
import monRDV.model.Specialite;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryCreneauDisponible;
import monRDV.repository.IRepositoryLieu;
import monRDV.repository.IRepositoryModalite;
import monRDV.repository.IRepositoryMotif;
import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositoryRendezVous;
import monRDV.repository.IRepositorySpecialite;
import monRDV.repository.IRepositoryUtilisateur;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonRdvBootApplicationTests {

	@Autowired
	private IRepositoryCreneauDisponible repoCreneauDisponible;
	@Autowired
	private IRepositoryLieu repoLieu;
	@Autowired
	private IRepositoryModalite repoModalite;
	@Autowired
	private IRepositoryMotif repoMotif;
	@Autowired
	private IRepositoryPatient repoPatient;
	@Autowired
	private IRepositoryPraticien repoPraticien;
	@Autowired
	private IRepositoryRendezVous repoRendezVous;
	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;
	@Autowired
	private IRepositorySpecialite repoSpecialite;

	@Test
	public void contextLoads() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

//		 debut specialite -------------------------------------------------------------------------

		List<Specialite> specialites1 = new ArrayList<>();
		List<Specialite> specialites2 = new ArrayList<>();
		List<Specialite> specialites3 = new ArrayList<>();

//		List<Specialite> specialites1 = daoSpecialite.findAll();
//		System.out.println("Nombre au d�but=" + specialites1.size());

		Specialite specialite1 = new Specialite();
		specialite1.setLibelle("proctologie");

		Specialite specialite2 = new Specialite();
		specialite2.setLibelle("gynecologie");

		Specialite specialite3 = new Specialite();
		specialite3.setLibelle("urologie");

		Specialite specialite4 = new Specialite();
		specialite4.setLibelle("cardiologie");

		Specialite specialite5 = new Specialite();
		specialite5.setLibelle("orthopédie");
		
		
		specialites1.add(specialite1);
		specialites1.add(specialite2);
		specialites1.add(specialite3);
		specialites1.add(specialite4);
		specialites1.add(specialite5);
		

		specialite1 = repoSpecialite.save(specialite1);
		specialite2 = repoSpecialite.save(specialite2);
		specialite3 = repoSpecialite.save(specialite3);
		specialite4 = repoSpecialite.save(specialite4);
		specialite5 = repoSpecialite.save(specialite5);



//		Specialite specialite1Find = daoSpecialite.find(specialite1.getId());
//		System.out.println(specialite1.getId() + "=" + specialite1Find.getId());
//		specialites1 = daoSpecialite.findAll();
//		System.out.println("Nombre avant la suppression=" + specialites1.size());
//		daoSpecialite.delete(spe2);

//		Debut motif -------------------------------------------------------------------------------------------
		List<Motif> motifs1 = new ArrayList<>();

		Motif motif1 = new Motif();
		motif1.setLibelle("contrôle des 50 ans");
		motif1.setSpecialite(specialite1);
		motifs1.add(motif1);

		motif1 = repoMotif.save(motif1);

//		d�but utilisateur ------------------------------------------------------------------------------

		List<Utilisateur> utilisateurs = repoUtilisateur.findAll();

//		System.out.println("Nombre au d�but=" + utilisateurs.size());

		Utilisateur utilisateur1 = new Utilisateur(); // new

		utilisateur1.setEmail("utilisateur1@gmail.com");
		utilisateur1.setTelephone("0669696969");
		utilisateur1.setDateCreation(new Date());
		utilisateur1.setMotDePasse("liugfhlkjs<fhKsuil<");
		utilisateur1.setProfil(Profil.Patient);

		utilisateur1 = repoUtilisateur.save(utilisateur1); // managed




//		Utilisateur utilisateur1Find = daoUtilisateur.find(utilisateur1.getId());
//		utilisateur1 = daoUtilisateur.save(utilisateur1); // managed
//		utilisateur1Find = daoUtilisateur.find(utilisateur1.getId());
//		utilisateurs = daoUtilisateur.findAll();
//		utilisateur1 = daoUtilisateur.save(utilisateur1); // managed
//		Patient ---------------------------------------------------------------------------------------
		List<Patient> patients = repoPatient.findAll();

		Patient patient1 = new Patient();
		patient1.setDefaut(true);
		patient1.setNom("Mouden");
		patient1.setPrenom("Charlotte");
		patient1.setDateNaissance(sdf.parse("05/10/1990"));
		patient1.setDateCreation(new Date());
//		patient1.setUtilisateur(utilisateur); fait plus loin
//		patient1.setListRendezVous(rendezVous); fait plus loin

		Patient patient2 = new Patient();
		patient2.setDefaut(true);
		patient2.setNom("Gonzales");
		patient2.setPrenom("Arthur");
		patient2.setDateNaissance(sdf.parse("30/12/1993"));
		patient2.setDateCreation(new Date());
//		patient2.setUtilisateur(utilisateur); fait plus loin
//		patient2.setListRendezVous(rendezVous); fait plus loin

		Patient patient3 = new Patient();
		patient3.setDefaut(true);
		patient3.setNom("Labat");
		patient3.setPrenom("Jory");
		patient3.setDateNaissance(sdf.parse("21/10/1992"));
		patient3.setDateCreation(new Date());
//		patient3.setUtilisateur(utilisateur); fait plus loin
//		patient3.setListRendezVous(rendezVous); fait plus loin

		patients.add(patient3);
		patients.add(patient2);
		patients.add(patient1);

		patient1 = repoPatient.save(patient1);
		patient2 = repoPatient.save(patient2);
		patient3 = repoPatient.save(patient3);

//		Modalite ------------------------------------------------------------------------------------------
		List<Modalite> modalites1 = new ArrayList<Modalite>();

		Modalite modalite1 = new Modalite();
		modalite1.setPrix(1.25f);
		modalite1.setDuree(15L);
		modalite1.setDelaiAnnulation(2L);
		modalite1.setDepassementHonoraires(false);
		modalite1.setMotif(motif1);
//		modalite1.setPraticien(praticien1);

		modalites1.add(modalite1);

		modalite1 = repoModalite.save(modalite1);

//		RendezVous ---------------------------------------------------------------------------------------------
		List<RendezVous> rendezvouss1 = new ArrayList<RendezVous>();

		RendezVous rendezvous1 = new RendezVous();
		rendezvous1.setUtilisateur(utilisateur1);
		rendezvous1.setPatient(patient1);
		rendezvous1.setModalite(modalite1);
		rendezvous1.setRendezVous(null);
		rendezvous1.setStatut(true);
		RendezVous rendezvous2 = new RendezVous();
		rendezvous2.setUtilisateur(utilisateur1);
		rendezvous2.setPatient(patient2);
		rendezvous2.setModalite(modalite1);
		rendezvous2.setStatut(true);
		rendezvous2.setRendezVous(null);
		RendezVous rendezvous3 = new RendezVous();
		rendezvous3.setUtilisateur(utilisateur1);
		rendezvous3.setPatient(patient3);
		rendezvous3.setModalite(modalite1);
		rendezvous3.setRendezVous(null);
		rendezvous3.setStatut(true);

		rendezvous1 = repoRendezVous.save(rendezvous1);
		rendezvous2 = repoRendezVous.save(rendezvous2);
		rendezvous3 = repoRendezVous.save(rendezvous3);

//		 Creation objets adresse -------------------------------------------------------------------------------------

		List<Adresse> adresses1 = new ArrayList<>();
		Adresse adresse1 = new Adresse();
		Adresse adresse2 = new Adresse();
		Adresse adresse3 = new Adresse();

		adresse1.setRue("10 rue Marechal Foch");
		adresse1.setCodePostal("65000");
		adresse1.setVille("Tarbes");
		adresse1.setInformations("4eme porte � gauche en partant de la 3 porte � droite derri�re la plante verte");

		adresse2.setRue("19 avenue du bobo");
		adresse2.setCodePostal("33000");
		adresse2.setVille("Bordeaux");
		adresse2.setInformations("L'entr�e de la villa se trouve � c�t� de la 4e Ferrari");

		adresse3.setRue("144 rue de l'huitre");
		adresse3.setCodePostal("33120");
		adresse3.setVille("Arcachon");
		adresse3.setInformations("Ne cherchez pas une maison, c'est une cabane de p�cheur");

//		Lieu ----------------------------------------------------------------------------------------------
		List<Lieu> lieux1 = new ArrayList<>();
		List<Lieu> lieux2 = new ArrayList<>();
		List<Lieu> lieux3 = new ArrayList<>();

		Lieu lieu1 = new Lieu();
		lieu1.setNom("Cabinet Caramel");
		lieu1.setAdresse(adresse1);
		Lieu lieu2 = new Lieu();
		lieu2.setAdresse(adresse2);
		lieu2.setNom("Hopital Nougat");
		Lieu lieu3 = new Lieu();
		lieu3.setNom("Cabinet Cacao");
		lieu3.setAdresse(adresse3);

		lieux1.add(lieu1);
		lieux1.add(lieu2);

		lieu1 = repoLieu.save(lieu1);
		lieu2 = repoLieu.save(lieu2);
		lieu3 = repoLieu.save(lieu3);

//		CreneauDisponible ----------------------------------------------------------------------------------
		List<CreneauDisponible> creneauxdisponibles1 = new ArrayList<>();

		CreneauDisponible creneaudisponible1 = new CreneauDisponible();
		creneaudisponible1.setDebut(sdf2.parse("01/12/2018 8:00"));
		creneaudisponible1.setFin(sdf2.parse("01/12/2018 8:15"));
		creneaudisponible1.setLieu(lieu1);
//		creneaudisponible1.setPraticien(praticien1); Fait plus loin
		creneaudisponible1.setRendezVous(rendezvous1);

		CreneauDisponible creneaudisponible2 = new CreneauDisponible();
		creneaudisponible2.setDebut(sdf2.parse("01/12/2018 8:15"));
		creneaudisponible2.setFin(sdf2.parse("01/12/2018 8:30"));
		creneaudisponible2.setLieu(lieu1);
//		creneaudisponible2.setPraticien(praticien1); Fait plus loin
		creneaudisponible2.setRendezVous(rendezvous1);

		CreneauDisponible creneaudisponible3 = new CreneauDisponible();

		creneaudisponible3.setDebut(sdf2.parse("01/12/2018 9:15"));
		creneaudisponible3.setFin(sdf2.parse("01/12/2018 9:30"));
		creneaudisponible3.setLieu(lieu2);
//		creneaudisponible2.setPraticien(praticien2); Fait plus loin
		creneaudisponible3.setRendezVous(rendezvous2);

		creneaudisponible1 = repoCreneauDisponible.save(creneaudisponible1);
		creneaudisponible2 = repoCreneauDisponible.save(creneaudisponible2);
		creneaudisponible3 = repoCreneauDisponible.save(creneaudisponible3);

//		Praticien -----------------------------------------------------------------------------------------------
		List<Praticien> praticiens1 = new ArrayList<>();
		List<Praticien> praticiens2 = new ArrayList<>();
		List<Praticien> praticiens3 = new ArrayList<>();

		Praticien praticien1 = new Praticien();
		praticien1.setNom("House");
		praticien1.setPrenom("Gregory");
		praticien1.setPrendCarteVitale(true);
		praticien1.setValidationAuto(true);
		praticien1.getSpecialites().add(specialite3);
		praticien1.getSpecialites().add(specialite4);
		praticien1.getSpecialites().add(specialite5);
		praticien1.getLieux().add(lieu3);
		praticien1.setLieux(lieux1);
		praticien1.setModalites(modalites1);
		praticien1.setCreneauDisponibles(creneauxdisponibles1);
		

		Praticien praticien2 = new Praticien();
		praticien2.setNom("Tapie");
		praticien2.setPrenom("Bernard");
		praticien2.setPrendCarteVitale(true);
		praticien2.setValidationAuto(true);
		praticien2.setLieux(lieux1);
		praticien2.getSpecialites().add(specialite1);
		praticien2.getSpecialites().add(specialite3);
		praticien2.getSpecialites().add(specialite5);
		
////		praticien2.setSpecialites(specialites1);  //  /!\ le setSpecialite ne fonctionne pas

		praticien2.setLieux(lieux2);
		praticien2.setModalites(modalites1);
		praticien2.setCreneauDisponibles(creneauxdisponibles1);
		
		Praticien praticien3 = new Praticien();
		praticien3.setNom("Masse");
		praticien3.setPrenom("Gregoire");
		praticien3.setPrendCarteVitale(true);
		praticien3.setValidationAuto(true);
		praticien3.setSpecialites(specialites3);
		praticien3.setLieux(lieux3);
		praticien3.setModalites(modalites1);
		praticien3.getSpecialites().add(specialite1);
		praticien3.getSpecialites().add(specialite4);
		praticien3.getSpecialites().add(specialite5);
		praticien3.setCreneauDisponibles(creneauxdisponibles1);

		praticiens1.add(praticien1);
		praticiens1.add(praticien2);
		praticiens1.add(praticien3);

		praticien1 = repoPraticien.save(praticien1);
		praticien2 = repoPraticien.save(praticien2);
		praticien3 = repoPraticien.save(praticien3);
		
		Utilisateur utilisateur2 = new Utilisateur(); // new
		utilisateur2.setEmail("utilisateur2@gmail.com");
		utilisateur2.setTelephone("0654879865");
		utilisateur2.setDateCreation(new Date());
		utilisateur2.setMotDePasse("ettasoeur");
		utilisateur2.setProfil(Profil.Praticien);
		utilisateur2.setPraticien(praticien1);
		utilisateur2 = repoUtilisateur.save(utilisateur2); // managed

//		Liens restants ---------------------------------------------------------------------------------

		creneaudisponible1.setPraticien(praticien1);
		creneaudisponible2.setPraticien(praticien1);
		creneaudisponible2.setPraticien(praticien2);

		lieu3.setPraticien(praticien1);

		modalite1.setPraticien(praticien1);
		modalite1.setRendezVous(rendezvouss1);

		patient1.setUtilisateur(utilisateur1);
		patient1.setListRendezVous(rendezvouss1);
		patient2.setUtilisateur(utilisateur1);
		patient2.setListRendezVous(rendezvouss1);
		patient3.setUtilisateur(utilisateur1);
		patient3.setListRendezVous(rendezvouss1);

//		Saves finale ------------------------------------------------------------------------------------
		creneaudisponible1 = repoCreneauDisponible.save(creneaudisponible1);
		creneaudisponible2 = repoCreneauDisponible.save(creneaudisponible2);
		creneaudisponible3 = repoCreneauDisponible.save(creneaudisponible3);

		lieu1 = repoLieu.save(lieu1);
		lieu2 = repoLieu.save(lieu2);
		lieu3 = repoLieu.save(lieu3);

		rendezvous1 = repoRendezVous.save(rendezvous1);
		rendezvous2 = repoRendezVous.save(rendezvous2);
		rendezvous3 = repoRendezVous.save(rendezvous3);

		patient1 = repoPatient.save(patient1);
		patient2 = repoPatient.save(patient2);
		patient3 = repoPatient.save(patient3);

		modalite1 = repoModalite.save(modalite1);
//		specialite1.getPraticiens().add(praticien2);

		praticien2 = repoPraticien.save(praticien2);

		specialite1 = repoSpecialite.save(specialite1);
		specialite2 = repoSpecialite.save(specialite2);
		specialite3 = repoSpecialite.save(specialite3);
		specialite4 = repoSpecialite.save(specialite4);
		specialite5 = repoSpecialite.save(specialite5);
	}

}
