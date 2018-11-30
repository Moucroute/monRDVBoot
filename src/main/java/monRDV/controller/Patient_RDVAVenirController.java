package monRDV.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.Patient;
import monRDV.model.RendezVous;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryRendezVous;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/monComptePatient")
public class Patient_RDVAVenirController {

	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;

	@Autowired
	private IRepositoryPatient repoPatient;

	@Autowired
	private IRepositoryRendezVous repoRendezVous;

	public Patient_RDVAVenirController() {
		super();

	}

	@GetMapping({ "/", "/rdvAVenir" }) // ETAPE 1
	public String listvide() {

		return "patient/patientRDVAVenir"; // ETAPE 4
	}

	@GetMapping({ "/yo", "/rdvAVenir" }) // ETAPE 1
	public String list(@RequestParam Long id, Model model) {
		Optional<Utilisateur> optUtilisateur = repoUtilisateur.findById(id);

		if (optUtilisateur.isPresent()) {
			Utilisateur utilisateur = optUtilisateur.get();
		}
		List<Patient> patients = repoPatient.findByUtilisateur(id);
		List<RendezVous> rendezvouss = repoRendezVous.findByUtilisateur(id);

		
//		model.addAttribute("page", "eleve");

		model.addAttribute("rdvAVenirRDV", rendezvouss);
		model.addAttribute("rdvAVenirPatients", patients); // ETAPE 3

		return "patient/patientRDVAVenir"; // ETAPE 4
	}

}
