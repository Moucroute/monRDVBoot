package monRDV.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.CreneauDisponible;
import monRDV.model.RendezVous;
import monRDV.repository.IRepositoryCreneauDisponible;
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
	@Autowired
	
	private IRepositoryCreneauDisponible repoCreneauDisponible;

	public Patient_RDVAVenirController() {
		super();

	}

	@GetMapping("/rdvAVenir") // ETAPE 1
	public String list(@RequestParam Long id, Model model) {
		
		List<RendezVous> rendezvouss = repoRendezVous.findByUtilisateur(id);
		model.addAttribute("rdvAVenirRDV", rendezvouss);
		
		List<CreneauDisponible> creneaux = repoCreneauDisponible.findByUtilisateur(id);
		model.addAttribute("rdvAVenirCreneaux", creneaux);
		
		
		return "patient/patientRDVAVenir"; // ETAPE 4
	}

}
