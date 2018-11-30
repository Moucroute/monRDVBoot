package monRDV.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import monRDV.model.Utilisateur;
import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IRepositoryPatient repoPatient;
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

	@GetMapping("/save")
	public String save(@ModelAttribute("inscriptionFormPatient") @Valid InscriptionFormPatient inscriptionFormPatient,
			BindingResult result, Model model) {

		model.addAttribute("page", "patient");

		Patient nouveauPatient = new Patient();
		nouveauPatient.setNom(inscriptionFormPatient.getNom());
		nouveauPatient.setPrenom(inscriptionFormPatient.getPrenom());
//		nouveauPatient.setDateNaissance(inscriptionFormPatient.getPrenom());
		nouveauPatient.setDateCreation(new Date());
		nouveauPatient.setDefaut(true);

//		Utilisateur nouvelUtilisateur = new Utilisateur();
//		nouvelUtilisateur.setTelephone(inscriptionFormPatient.getTelephone());
		repoPatient.save(nouveauPatient);

		return "patient/inscriptionPatient";
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
	public String findWithPatients(@RequestParam Long id, Model model) {
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
}
