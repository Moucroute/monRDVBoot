package monRDV.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import monRDV.model.Praticien;
import monRDV.repository.IRepositoryPraticien;

@Controller
@RequestMapping("/homerecherche")
public class HomeControlerRecherche {
	
	@Autowired
	private IRepositoryPraticien repoPraticien;

	public HomeControlerRecherche() {
		super();

	}

	@GetMapping({ "/", "/list" }) // ETAPE 1
	public String list(Model model) { 
		List<Praticien> praticiens = repoPraticien.findAll(); // ETAPE 2

		model.addAttribute("page", "accueil");
		model.addAttribute("mesPraticiens", praticiens); // ETAPE 3

		return "home/homerecherche"; // ETAPE 4
	}

}
