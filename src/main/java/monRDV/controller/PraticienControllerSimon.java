package monRDV.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.Praticien;
import monRDV.model.Specialite;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositorySpecialite;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/praticien")
public class PraticienControllerSimon {

	@Autowired
	IRepositorySpecialite repoSpecialite;

	@Autowired
	IRepositoryPraticien repoPraticien;

	@Autowired
	IRepositoryUtilisateur repoUtilisateur;

//	@GetMapping({ "/connexion" })
//	public String connexion(@RequestParam String email, Model model) {
//
//		Utilisateur utilisateur = null;
//		utilisateur = repoUtilisateur.findByEmail(email);
//
//		idUtilisateur = utilisateur.getId();
//
//		return "praticien/praticienEdit2"; // ETAPE 4
//	}

	@GetMapping("/editMesSpecialites")
	public String praticienEdit2(@RequestParam Long utilisateurId, Model model) {

		List<Specialite> mesSpecialites = repoSpecialite.findByUtilisateurId(utilisateurId);
		List<Specialite> allSpecialites = repoSpecialite.findAll();

		model.addAttribute("mesSpecialites", mesSpecialites);
		model.addAttribute("allSpecialites", allSpecialites);
		model.addAttribute("utilisateurId", utilisateurId);

		return "praticien/praticienEdit2"; // ETAPE 4

	}

	@GetMapping("/deleteSpecialite")
	public String deleteSpecialite(@RequestParam Long specialiteId, @RequestParam Long utilisateurId) {

		Utilisateur utilisateur = repoUtilisateur.findById(utilisateurId).get();
		Praticien praticien = utilisateur.getPraticien();
		Specialite specialite = repoSpecialite.findById(specialiteId).get();
		List<Specialite> specialites = praticien.getSpecialites();
		specialites.remove(specialite);
		repoPraticien.save(praticien);

		return "redirect:editMonCompte?utilisateurId=" + utilisateurId; // TODO : Faire valider la méthode par Eric

	}

	@PostMapping("/addSpecialites")
	public String addSpecialites(@RequestParam Long utilisateurId, @RequestParam List<Long> checkedSpecialites) {

		Utilisateur utilisateur = repoUtilisateur.findById(utilisateurId).get();
		Praticien praticien = utilisateur.getPraticien();
		Specialite specialite = null;

		List<Specialite> specialites = praticien.getSpecialites();

		specialites.removeAll(specialites);

		for (Long specialiteId : checkedSpecialites) {
			specialite = repoSpecialite.findById(specialiteId).get();
			specialites.add(specialite);
		}

		repoPraticien.save(praticien);

		return "redirect:editMesSpecialites?utilisateurId=" + utilisateurId; // TODO : Faire valider la méthode par Eric

	}

	@PostMapping("/addNewSpecialite")
	public String addNewSpecialite(@RequestParam Long utilisateurId, @RequestParam String newSpecialite) {

		Specialite specialite = new Specialite();
		specialite.setLibelle(newSpecialite);
		
		repoSpecialite.save(specialite);

		return "redirect:editMesSpecialites?utilisateurId=" + utilisateurId; // TODO : Faire valider la méthode par Eric

	}
	
	@GetMapping("/editMesMotifsConsultation")
	public String praticienEdit3(@RequestParam Long utilisateurId, Model model) {


		model.addAttribute("utilisateurId", utilisateurId);

		return "praticien/praticienEdit3"; // ETAPE 4

	}

}
