package monRDV.controller;

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

import monRDV.model.Patient;
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

	@GetMapping({ "/", "/list" }) // ETAPE 1
	public String list(Model model) {
		List<Patient> patients = repoPatient.findAll(); // ETAPE 2

		model.addAttribute("page", "patient");
		model.addAttribute("mesPatients", patients); // ETAPE 3
		return "patient/patients"; // ETAPE 4
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("page", "patient");
		model.addAttribute("patient", new Patient());
		
		return "patient/patientEdit";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		model.addAttribute("page", "patient");
		Optional<Patient> opt = repoPatient.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("eleve", opt.get());
		} else {
			model.addAttribute("eleve", new Patient());
		}

		return "patient/patientEdit";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("eleve") @Valid Patient patient, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("page", "patient");
			model.addAttribute("patients", repoPatient.findAll());

			return "eleve/eleveEdit";
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
