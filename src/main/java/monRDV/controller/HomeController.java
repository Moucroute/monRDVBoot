package monRDV.controller;

//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monRDV.model.Praticien;
import monRDV.repository.IRepositoryLieu;
import monRDV.repository.IRepositoryPraticien;
import monRDV.repository.IRepositorySpecialite;

@Controller
public class HomeController {

	@Autowired
	private IRepositorySpecialite repoSpecialite;
	
	@Autowired
	private IRepositoryPraticien repoPraticien;
	
	@Autowired
	private IRepositoryLieu repoLieu;

	public HomeController() {
		super();
	}

	@GetMapping("")
	public String home(Model model) {

		model.addAttribute("page", "accueil");
		model.addAttribute("specialites", repoSpecialite.findAll()); // ETAPE 3
		model.addAttribute("lieux", repoLieu.findAll());

		return "home/home";
	}


	@GetMapping("/list" ) // ETAPE 1
	public String list(Model model) { 
		List<Praticien> praticiens = repoPraticien.findAll(); // ETAPE 2

		model.addAttribute("page", "accueil");
		model.addAttribute("mesPraticiens", praticiens); // ETAPE 3

		return "home/homerecherche"; // ETAPE 4
	}
	
	@PostMapping("/listByVille")
	public String listByVille(Model model, @RequestParam String ville) { 
		
		List<Praticien> praticiens = repoPraticien.findByVille(ville); 
		model.addAttribute("ville", ville);
		model.addAttribute("page", "accueil");
		model.addAttribute("mesPraticiens", praticiens); 

		return "home/homerecherche"; 
	}
	
	@PostMapping("/listBySpecialite")
	public String listBySpecialite(Model model, @RequestParam("specialite") String libelle) { 
		
		List<Praticien> praticiens = repoPraticien.findBySpecialite(libelle);
		model.addAttribute("libelle", libelle);
		model.addAttribute("page", "accueil");
		model.addAttribute("mesPraticiens", praticiens); 

		return "home/homerecherche"; 
	}
	
//	@PostMapping("/listByDate")
//	public String listByDate(Model model, @RequestParam("debut") Date debut) { 
//		
//		List<Praticien> praticiens = repoPraticien.findByDate(debut);
//		model.addAttribute("debut", debut);
//		model.addAttribute("page", "accueil");
//		model.addAttribute("mesPraticiens", praticiens); 
//
//		return "home/homerecherche"; 
//	}
	
	
//	@PostMapping("/listByVilleSpecialite")
//	public String listByVilleSpecialite(Model model, @RequestParam String ville, @RequestParam String libelle) { 
//		
//		List<Praticien> praticiens = repoPraticien.findBySpecialite(libelle);
//		model.addAttribute("libelle", libelle);
//		model.addAttribute("ville", ville);
//		model.addAttribute("page", "accueil");
//		model.addAttribute("mesPraticiens", praticiens); 
//
//		return "home/homerecherche"; 
//	}
	

//	@PostMapping("/sortByDate")
//	public String sortByDate(Model model) { 
//		
//		
//		List<Praticien> praticiens = repoPraticien.orderByCreneau();
//		model.addAttribute("page", "accueil");
//		model.addAttribute("mesPraticiens", praticiens); 
//
//		return "home/homerecherche"; 
//	}
	
//	@GetMapping({"/sort"}) // ETAPE 1
//	public String sort(Model model, @RequestParam String by) { 
//		
//		if (by == "price") {
//			List<Praticien> praticiens = repoPraticien.findAll();
//			
//		}
//		else if (by == "place") {
//			List<Praticien> praticiens = repoPraticien.findAll();
//			
//		}
//		else if (by == "dispo") {
//			List<Praticien> praticiens = repoPraticien.findAll();
//			
//		}
//
//
//	}
}
