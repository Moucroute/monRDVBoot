package monRDV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositoryUtilisateur;

@Controller
@RequestMapping("/praticien")
public class PraticienControllerLaura {
	
	
	private IRepositoryPraticien repoPraticien;
	@Autowired
	private IRepositoryUtilisateur repoUtilisateur;
	
	
	public PraticienControllerLaura() {
		super();
	}

	
	@GetMapping("/inscription")
	public String inscription(Model model) {
//		model.addAttribute("page", "patient");
//		model.addAttribute("patient", new Patient());
		
		return "praticien/inscriptionPatient";
	}
	
	
	
}
