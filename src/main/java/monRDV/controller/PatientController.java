package monRDV.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.InscriptionFormPatient;
import monRDV.model.Patient;
import monRDV.model.Profil;
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IRepositoryPatient repoPatient;
	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;

	public PatientController() {
		super();

	}

	@GetMapping({ "/", "/mesInfosPatient" }) // ETAPE 1
	public String list(Model model) {
		List<Patient> patients = repoPatient.findAll(); // ETAPE 2

		model.addAttribute("page", "patient");
		model.addAttribute("mesPatients", patients); // ETAPE 3
		return "patient/mesInfosPatient"; // ETAPE 4
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("page", "patient");
		model.addAttribute("patient", new Patient());

		return "patient/mesInfosPatient";
	}

	@GetMapping("/inscription")
	public String inscription(Model model) {
		model.addAttribute("page", "patient");
		model.addAttribute("inscriptionFormPatient", new InscriptionFormPatient());

		return "patient/inscriptionPatient";
	}

	@RequestMapping("/saveInscription")
	public String saveInscription(
			@ModelAttribute("inscriptionFormPatient") @Valid InscriptionFormPatient inscriptionFormPatient,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("inscriptionFormPatient", inscriptionFormPatient);
			model.addAttribute("page", "patient");
			return "patient/inscriptionPatient";
		} else {
			model.addAttribute("page", "patient");
			Patient nouveauPatient = new Patient();
			nouveauPatient.setNom(inscriptionFormPatient.getNom());
			nouveauPatient.setPrenom(inscriptionFormPatient.getPrenom());
			nouveauPatient.setDateCreation(new Date());
			nouveauPatient.setDefaut(true);
			nouveauPatient.setDateNaissance(inscriptionFormPatient.getDtNaissance());
			Utilisateur nouvelUtilisateur = new Utilisateur();
			nouvelUtilisateur.setTelephone(inscriptionFormPatient.getTelephone());
			nouvelUtilisateur.setProfil(Profil.Patient);
			nouvelUtilisateur.setDateCreation(new Date());
			nouvelUtilisateur.setEmail(inscriptionFormPatient.getEmail());
			nouvelUtilisateur.setMotDePasse(inscriptionFormPatient.getMotDePasse());

			repoPatient.save(nouveauPatient);
			repoUtilisateur.save(nouvelUtilisateur);

			return "patient/inscriptionPatientValidation";
		}
	}

//	@GetMapping("/edit")
//	public String edit(@RequestParam Long id, Model model) {
//		model.addAttribute("page", "patient");
//		Optional<Patient> opt = repoPatient.findById(id);
//		if (opt.isPresent()) {
//			model.addAttribute("patient", opt.get());
//		} else {
//			model.addAttribute("patient", new Patient());
//		}
//
//		return "patient/mesInfosPatient";
//	}

	@GetMapping("/editMesInfosPatient")
	public String editInfosPatients(@RequestParam Long id, Model model) {
		List<Patient> patientsUt = new ArrayList<Patient>();

		model.addAttribute("page", "patient");
		Utilisateur utilisateur = repoUtilisateur.findWithPatients(id);
		if (utilisateur != null) {
			model.addAttribute("utilisateur", utilisateur);
			for (Patient patient : utilisateur.getPatients()) {
				if (patient.getDefaut()) {
					model.addAttribute("patient", patient);
				} else {
					patientsUt.add(patient);
				}
			}
		} else {
			model.addAttribute("utilisateur", new Utilisateur());
			model.addAttribute("patient", new Patient());
		}

		model.addAttribute("patientsUt", patientsUt);

		return "patient/mesInfosPatient";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("patient") @Valid Patient patient, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("page", "patient");
			model.addAttribute("patients", repoPatient.findAll());

			return "patient/mesInfosPatient";
		}

		repoPatient.save(patient);

		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		Optional<Patient> opt = repoPatient.findById(id);

		if (opt.isPresent()) {
			repoPatient.delete(opt.get());
		}

		return "forward:list";
	}

	@RequestMapping("/connexion")
	public String connexion(@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur, BindingResult result,
			Model model) {
		if (repoUtilisateur.findWithEmail(utilisateur.getEmail()) != null) {
			Utilisateur nouvelUtilisateur = repoUtilisateur.findWithEmail(utilisateur.getEmail());
			if (nouvelUtilisateur.getMotDePasse().equals(utilisateur.getMotDePasse()) ) {
				System.out.println(
						"00000000000000000000000000000000 L'utilisateur est connecté!!! 0000000000000000000000000000000000");
				return "patient/inscriptionPatientValidation";
			} else {
				System.out.println(
						"00000000000000000000000000000000 Le mot de passe n'est pas correct!! 0000000000000000000000000000000000  " + nouvelUtilisateur.getMotDePasse() + " = " + utilisateur.getMotDePasse());

				return "patient/inscriptionPatientValidation";

			}

		} else

		{
			System.out.println(
					"00000000000000000000000000000000 L'utilisateur n'existe pas 0000000000000000000000000000000000");
			return "patient/inscriptionPatientValidation";
		}
	}

	@RequestMapping("/seConnecter")
	public String seConnecter(Model model) {
		model.addAttribute("page", "patient");
		model.addAttribute("utilisateur", new Utilisateur());

		return "home/connexionTest";
	}
}
