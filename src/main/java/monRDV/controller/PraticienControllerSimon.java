package monRDV.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/editMonCompte")
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

		return "redirect:/editMonCompte"; // ETAPE 4

	}

}
