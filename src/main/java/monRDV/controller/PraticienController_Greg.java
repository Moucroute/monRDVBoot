package monRDV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.Praticien;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/praticien")
public class PraticienController_Greg {

	@Autowired
	private IRepositoryPraticien repoPraticien;

	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;

	public PraticienController_Greg() {
		super();
	}

	@GetMapping({ "/moncompte" })
	public String list(@RequestParam Long utilisateurId, Model model) {

//		model.addAttribute("edit", "list");

		Utilisateur utilisateur = repoUtilisateur.findById(utilisateurId).get();
		Praticien praticien = utilisateur.getPraticien();
		
		Praticien opt1 = repoPraticien.findWithLieux(praticien.getId());

		model.addAttribute("monPraticien", opt1);
		model.addAttribute("utilisateurId", utilisateurId);

		return "praticien/praticienEdit1";
	}

//	@GetMapping("/edit")
//	public String edit(Model model) {
//		
//		model.addAttribute("edit", "edit");
//
//		Optional<Praticien> opt = repoPraticien.findById(44L);
//		if (opt.isPresent()) {
//			model.addAttribute("monPraticien", opt.get());
//		} else {
//			model.addAttribute("monPraticien", new Praticien());
//		}
//
//		return "praticien/praticienEdit1";
//	}

	@PostMapping("/save")
	public String save(@RequestParam Long utilisateurId, @ModelAttribute("monPraticien") Praticien praticien, Model model) {

		Utilisateur utilisateur = repoUtilisateur.findById(utilisateurId).get();
		Praticien praticien1 = utilisateur.getPraticien();
		praticien1.setNom(praticien.getNom());
		praticien1.setPrenom(praticien.getPrenom());
		praticien1.getUtilisateur().setEmail(praticien.getUtilisateur().getEmail());
		praticien1.getUtilisateur().setMotDePasse(praticien.getUtilisateur().getMotDePasse());

		repoPraticien.save(praticien1);

		return "redirect:moncompte?utilisateurId="+utilisateurId;
	}

}
