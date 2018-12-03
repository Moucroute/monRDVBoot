package monRDV.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import monRDV.model.InscriptionFormPraticien;
import monRDV.model.Praticien;
import monRDV.model.Specialite;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositorySpecialite;
import monRDV.repository.IRepositoryUtilisateur;


import javax.swing.JOptionPane;

@Controller
@RequestMapping("/praticien")
public class PraticienControllerLaura {

	@Autowired
	private IRepositoryPraticien repoPraticien;
	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;
	@Autowired
	private IRepositorySpecialite repoSpecialite;

	public PraticienControllerLaura() {
		super();
	}

	@GetMapping("/inscription")
	public String inscription(Model model) {
		model.addAttribute("page", "praticien");
		model.addAttribute("inscriptionFormPraticien", new InscriptionFormPraticien());
		model.addAttribute("specialites", repoSpecialite.findAll());

		return "praticien/inscriptionPraticien";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("page", "praticien");
		model.addAttribute("praticien", new Praticien());
		model.addAttribute("specialites", repoSpecialite.findAll());

		return "praticien/inscriptionPraticien";
	}

	@RequestMapping("/save")
	public String save(
			@ModelAttribute("inscriptionFormPraticien") @Valid InscriptionFormPraticien inscriptionFormPraticien,
			BindingResult result, Model model) {

		model.addAttribute("page", "praticien");

		Praticien nouveauPraticien = new Praticien();

		Utilisateur nouvelUtilisateur = new Utilisateur();

		nouveauPraticien.setNom(inscriptionFormPraticien.getNom());

		nouveauPraticien.setPrenom(inscriptionFormPraticien.getPrenom());

		nouveauPraticien.setPrendCarteVitale(inscriptionFormPraticien.getPrendCarteVitale());

		nouvelUtilisateur.setEmail(inscriptionFormPraticien.getEmail());

		if ((inscriptionFormPraticien.getSpecialite().equals(null)) || (inscriptionFormPraticien.getSpecialite().equals(""))) {
			
			nouveauPraticien.setSpecialites(inscriptionFormPraticien.getSpecialites());	

		} else {
			
			// Ajout de la spécialité entrée à la main à notre liste de spécialités
			
			// Attention, il faudra rajouter la validation de l'admin

			List<Specialite> specs = inscriptionFormPraticien.getSpecialites();
			
			Specialite spe = new Specialite();
			
			spe.setLibelle(inscriptionFormPraticien.getSpecialite());
			
			spe.setVersion(0);

			specs.add(spe);
			
			repoSpecialite.saveAll(specs);

			nouveauPraticien.setSpecialites(specs);

		}

		if (inscriptionFormPraticien.getMotDePasse().equals(inscriptionFormPraticien.getConfirmationMotDePasse())) {

			nouvelUtilisateur.setMotDePasse(inscriptionFormPraticien.getMotDePasse());
		}

		else {
			
			//JOptionPane.showMessageDialog(null, "Vos mots de passe doivent être identiques", "Erreur de Saisie", JOptionPane.WARNING_MESSAGE);
	        
	        return "redirect:praticienEdit1";
			

		}
		
		
		nouvelUtilisateur.setPraticien(nouveauPraticien);

		repoPraticien.save(nouveauPraticien);

		repoUtilisateur.save(nouvelUtilisateur);

		return "praticien/inscriptionPraticien";
	}
	

	

	public IRepositoryPraticien getRepoPraticien() {
		return repoPraticien;
	}

	public void setRepoPraticien(IRepositoryPraticien repoPraticien) {
		this.repoPraticien = repoPraticien;
	}

	public IRepositoryUtilisateur getRepoUtilisateur() {
		return repoUtilisateur;
	}

	public void setRepoUtilisateur(IRepositoryUtilisateur repoUtilisateur) {
		this.repoUtilisateur = repoUtilisateur;
	}

}
