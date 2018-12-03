package monRDV.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import monRDV.model.Praticien;

import monRDV.repository.IRepositoryPraticien;





@Controller
@RequestMapping("/praticien")
public class PraticienController_Greg {
	
	@Autowired
	private IRepositoryPraticien repoPraticien;

	public PraticienController_Greg() {
		super();
	}
	
	@GetMapping({"/moncompte"})
	public String list(Model model) { 
		
		Praticien opt1 = repoPraticien.findWithLieux(44L);

		model.addAttribute("monPraticien", opt1);
		
		return "praticien/praticienEdit1"; // ETAPE 4
	}

}
